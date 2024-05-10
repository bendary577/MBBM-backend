package com.mbbm.app.service.youcan;

import com.google.gson.Gson;
import com.mbbm.app.controller.authentication.AuthenticationController;
import com.mbbm.app.enums.ESyncStatus;
import com.mbbm.app.enums.EYoucanPricingStrategy;
import com.mbbm.app.enums.EYoucanSyncType;
import com.mbbm.app.model.youcan.YoucanConfiguration;
import com.mbbm.app.model.youcan.YoucanIntegration;
import com.mbbm.app.util.compression.CompressionUtility;
import com.mbbm.app.youcan.YoucanSettings;
import com.mbbm.app.youcan.dto.products.YoucanProductDTO;
import com.mbbm.app.youcan.dto.products.YoucanProductsResponseDTO;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/***
 * @author mohamed.bendary
 * service responsible for product processing for background sync operations
 */
@Service
public class YoucanSyncProductService {

    @Autowired
    private CompressionUtility compressionUtility;

    private final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    public YoucanSyncProductService(){}

    public void syncProducts(YoucanIntegration youcanIntegration, YoucanConfiguration youcanConfiguration){
        List<YoucanProductDTO> productsList = getAllProducts(youcanIntegration.getToken());
        if(youcanConfiguration.getSyncType().value == EYoucanSyncType.GET_UPDATE.value){
            //update products with configured pricing policy
            updateAllProducts(youcanIntegration.getToken(), youcanConfiguration, productsList);
        }
        youcanConfiguration.setLastSyncStatus(ESyncStatus.SUCCESS);
    }

    public List<YoucanProductDTO> getAllProducts(String token){
        OkHttpClient client = new OkHttpClient();
        List<YoucanProductDTO> productsList = new LinkedList<>();

        String productsURL = YoucanSettings.YOUCAN_BASE_API_URL + "/products?include=variants";

        Request request = new Request.Builder()
                .url(productsURL)
                .get()
                .addHeader("Authorization", "Bearer "+token)
                .addHeader("Accept", "application/json")
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                int totalProductsPagesCount;
                int currentPageCount;
                Gson gson = new Gson();
                String decodedResponseBody = compressionUtility.decode(response.body().bytes());
                YoucanProductsResponseDTO youcanProductsResponseDTO = gson.fromJson(decodedResponseBody, YoucanProductsResponseDTO.class);
                productsList.addAll(youcanProductsResponseDTO.getData());
                totalProductsPagesCount = youcanProductsResponseDTO.getMeta().getPagination().getTotal_pages();
                currentPageCount = youcanProductsResponseDTO.getMeta().getPagination().getCurrent_page();
                //get all list of products
                do{
                    currentPageCount += 1;
                    List<YoucanProductDTO> fetchedProductsList = getProductsPage(currentPageCount, token);
                    if(!fetchedProductsList.isEmpty()){
                        productsList.addAll(fetchedProductsList);
                    }
                }while(currentPageCount+1 <= totalProductsPagesCount);
            } else {
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return productsList;
    }
    public List<YoucanProductDTO> getProductsPage(int pageCount, String token){
        OkHttpClient client = new OkHttpClient();
        List<YoucanProductDTO> productDTOList = new LinkedList<>();

        String productsURL = YoucanSettings.YOUCAN_BASE_API_URL + "/products?include=variants&page="+pageCount;

        Request request = new Request.Builder()
                .url(productsURL)
                .get()
                .addHeader("Authorization", "Bearer "+token)
                .addHeader("Accept", "application/json")
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                Gson gson = new Gson();
                String decodedResponseBody = compressionUtility.decode(response.body().bytes());
                YoucanProductsResponseDTO youcanProductsResponseDTO = gson.fromJson(decodedResponseBody, YoucanProductsResponseDTO.class);
                productDTOList.addAll(youcanProductsResponseDTO.getData());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return productDTOList;
    }

    public void updateAllProducts(String token, YoucanConfiguration youcanConfiguration, List<YoucanProductDTO> productDTOList) {
        try {
            for (YoucanProductDTO youcanProductDTO : productDTOList) {

                HashMap<String, Object> updatedData = new HashMap<>();
                //TODO:REVISE THIS CALCULATION
                if(youcanConfiguration.isUpdatePrices()){
                    double newPrice = 0;
                    double percentage;
                    EYoucanPricingStrategy pricingStrategy = youcanConfiguration.getPricingStrategy();
                    if(pricingStrategy.equals(EYoucanPricingStrategy.RATE_PER_PRODUCT)){
                        percentage = ((double) youcanConfiguration.getProductPricingRate() / youcanProductDTO.getCost_price()) * 100;
                        newPrice = youcanProductDTO.getCost_price() + percentage;
                    }
                    updatedData.put("price", newPrice);
                }

                if(youcanConfiguration.isUpdateVisibility() && !youcanProductDTO.isVisibility()){
                    updatedData.put("visibility", true);
                }

                MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
                for (Map.Entry<String, Object> entry : updatedData.entrySet()) {
                    String updatedAttributeKey = entry.getKey();
                    Object updatedAttributeValue = entry.getValue();
                    builder.addFormDataPart(updatedAttributeKey, updatedAttributeValue.toString());
                }
                MultipartBody multipartBody = builder.build();

                Request request = new Request.Builder()
                        .url(YoucanSettings.YOUCAN_BASE_API_URL + "/products/update/" + youcanProductDTO.getId())
                        .post(multipartBody)
                        .addHeader("Authorization", "Bearer " + token)
                        .addHeader("Accept", "application/json")
                        .build();

                OkHttpClient client = new OkHttpClient();
                Call call = client.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (response.isSuccessful()) {
                            System.out.println("Response Successfull");
                        } else {
                            System.out.println("Response Error");
                        }
                    }

                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                    }
                });

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
