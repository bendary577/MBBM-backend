package com.mbbm.app.service.youcan;

import com.google.gson.Gson;
import com.mbbm.app.dto.youcan.YoucanProductUpdateDTO;
import com.mbbm.app.dto.youcan.YoucanProductUpdateFileDataDTO;
import com.mbbm.app.exception.youcan.YoucanGetProductsException;
import com.mbbm.app.exception.youcan.YoucanUpdateProductException;
import com.mbbm.app.http.request.youcan.YoucanMassProductUpdateRequestDTO;
import com.mbbm.app.repository.YoucanIntegrationRepository;
import com.mbbm.app.repository.YoucanStoreRepository;
import com.mbbm.app.service.authentication.AuthenticationService;
import com.mbbm.app.service.ProfileService;
import com.mbbm.app.util.compression.CompressionUtility;
import com.mbbm.app.util.fileHandling.ExcelFileHandler;
import com.mbbm.app.youcan.YoucanSettings;
import com.mbbm.app.youcan.dto.YoucanProductUpdateRequestDTO;
import com.mbbm.app.youcan.dto.products.YoucanProductDTO;
import com.mbbm.app.youcan.dto.products.YoucanProductsResponseDTO;
import okhttp3.*;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.validation.constraints.NotNull;
import java.util.*;

/***
 * @author mohamed.bendary
 * service responsible for product processing in youcan product api
 */
@Service
public class YoucanProductService {

    @Autowired
    private ProfileService profileService;

    //TODO : repository should be accessible only in it's own service
    @Autowired
    private YoucanIntegrationRepository youcanIntegrationRepository;

    @Autowired
    private YoucanIntegrationService youcanIntegrationService;

    @Autowired
    private YoucanStoreRepository youcanStoreRepository;

    @Autowired
    private CompressionUtility compressionUtility;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private ExcelFileHandler excelFileHandler;

    @Autowired
    private StorageService storageService;

    private final Logger logger = LoggerFactory.getLogger(YoucanProductService.class);

    public YoucanProductService(){}

    public List<YoucanProductDTO> getAllProducts() throws Exception {
        List<YoucanProductDTO> productsList = new LinkedList<>();
        YoucanProductsResponseDTO youcanProductsResponseDTO = null;
        String youcanToken = youcanIntegrationService.getUserProfileYoucanIntegration().getToken();

        Request request = new Request.Builder()
                .url(YoucanSettings.YOUCAN_BASE_API_URL + "/products?include=variants")
                .get()
                .addHeader("Authorization", "Bearer "+youcanToken)
                .addHeader("Accept", "application/json")
                .build();

        OkHttpClient client = new OkHttpClient();
        Response response = client.newCall(request).execute();

        if (response.isSuccessful()) {
            int totalProductsPagesCount;
            int currentPageCount;
            Gson gson = new Gson();
            String decodedResponseBody = compressionUtility.decode(response.body().bytes());
            youcanProductsResponseDTO = gson.fromJson(decodedResponseBody, YoucanProductsResponseDTO.class);
            productsList.addAll(youcanProductsResponseDTO.getData());
            totalProductsPagesCount = youcanProductsResponseDTO.getMeta().getPagination().getTotal_pages();
            currentPageCount = youcanProductsResponseDTO.getMeta().getPagination().getCurrent_page();
            //get all list of products
            do{
                currentPageCount += 1;
                YoucanProductsResponseDTO fetchedProductsResponse = getProductsPerPage(currentPageCount);
                if(!fetchedProductsResponse.getData().isEmpty()){
                    productsList.addAll(fetchedProductsResponse.getData());
                }
            }while(currentPageCount+1 <= totalProductsPagesCount);
        } else {
            throw new YoucanGetProductsException("we encountered an error fetching youcan products");
        }
        return productsList;
    }

    public YoucanProductsResponseDTO getProductsPerPage(int pageCount) throws Exception {
        OkHttpClient client = new OkHttpClient();
        YoucanProductsResponseDTO youcanProductsResponseDTO;
        List<YoucanProductDTO> productDTOList = new LinkedList<>();
        String youcanIntegrationToken = this.youcanIntegrationService.getUserProfileYoucanIntegration().getToken();
        String productsURL = YoucanSettings.YOUCAN_BASE_API_URL + "/products?include=variants&page="+pageCount;

        Request request = new Request.Builder()
                .url(productsURL)
                .get()
                .addHeader("Authorization", "Bearer " + youcanIntegrationToken)
                .addHeader("Accept", "application/json")
                .build();

        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            Gson gson = new Gson();
            String decodedResponseBody = compressionUtility.decode(response.body().bytes());
            youcanProductsResponseDTO = gson.fromJson(decodedResponseBody, YoucanProductsResponseDTO.class);
            productDTOList.addAll(youcanProductsResponseDTO.getData());
            return youcanProductsResponseDTO;
        }else{
            throw new YoucanGetProductsException("we encountered an error fetching youcan products");
        }
    }

    /**
     * update specific product using key-value pair (Key : product attribute, value : product new value )
     * @param productId
     * @param youcanProductUpdateRequestDTO
     * @return
     * @throws Exception
     */
    public YoucanProductDTO updateProduct(String productId, @NotNull YoucanProductUpdateRequestDTO youcanProductUpdateRequestDTO) throws Exception {
        Response response;
        String youcanToken = youcanIntegrationService.getUserProfileYoucanIntegration().getToken();

        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        for (Map.Entry<String, Object> entry : youcanProductUpdateRequestDTO.getUpdatedData().entrySet()) {
            String updatedAttributeKey = entry.getKey();
            Object updatedAttributeValue = entry.getValue();
            builder.addFormDataPart(updatedAttributeKey, updatedAttributeValue.toString());
        }
        MultipartBody multipartBody = builder.build();

        Request request = new Request.Builder()
                .url(YoucanSettings.YOUCAN_BASE_API_URL + "/products/update/"+productId)
                .post(multipartBody)
                .addHeader("Authorization", "Bearer "+youcanToken)
                .addHeader("Accept", "application/json")
                .build();

        OkHttpClient client = new OkHttpClient();
        response = client.newCall(request).execute();
        String decodedResponseBody;
        if (response.isSuccessful()) {
            Gson gson = new Gson();
            decodedResponseBody = compressionUtility.decode(response.body().bytes());
            return gson.fromJson(decodedResponseBody, YoucanProductDTO.class);
        } else {
            throw new YoucanUpdateProductException("failed to update youcan store product");
        }
    }

    /**
     * update product using general configuration
     * @param youcanProductDTO
     * @return
     * @throws Exception
     */
    public YoucanProductDTO updateProduct(YoucanProductDTO youcanProductDTO, Map<String, Object> productValueUpdates) throws Exception {
        Response response;
        String youcanToken = youcanIntegrationService.getUserProfileYoucanIntegration().getToken();

        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        for (Map.Entry<String, Object> entry : productValueUpdates.entrySet()) {
            String updatedAttributeKey = entry.getKey();
            Object updatedAttributeValue = entry.getValue();
            builder.addFormDataPart(updatedAttributeKey, updatedAttributeValue.toString());
        }
        MultipartBody multipartBody = builder.build();

        Request request = new Request.Builder()
                .url(YoucanSettings.YOUCAN_BASE_API_URL + "/products/update/"+youcanProductDTO.getId())
                .post(multipartBody)
                .addHeader("Authorization", "Bearer "+youcanToken)
                .addHeader("Accept", "application/json")
                .build();

        OkHttpClient client = new OkHttpClient();
        response = client.newCall(request).execute();
        String decodedResponseBody;
        if (response.isSuccessful()) {
            Gson gson = new Gson();
            decodedResponseBody = compressionUtility.decode(response.body().bytes());
            return gson.fromJson(decodedResponseBody, YoucanProductDTO.class);
        } else {
            throw new YoucanUpdateProductException("failed to update youcan store product");
        }
    }

    /**
     * method used to update all user youcan store products asynchronously
     * this method can be called using reactive web service or async scheduler
     * @param youcanMassProductUpdateRequestDTO
     * @return
     * @throws Exception
     */
    public void updateAllProducts(YoucanMassProductUpdateRequestDTO youcanMassProductUpdateRequestDTO) throws Exception {

        String youcanToken = youcanIntegrationService.getUserProfileYoucanIntegration().getToken();
        //get all products
        List<YoucanProductDTO> youcanProductDTOList = getAllProducts();

        //prepare value updates hashmap
        HashMap<String, Object> productUpdatesValueMap = new HashMap<>();
        if(youcanMassProductUpdateRequestDTO.isUpdateVisibility()){
            productUpdatesValueMap.put("visibility", true); //update visibility
        }

        if(youcanMassProductUpdateRequestDTO.isUpdatePrice()){//update price
            productUpdatesValueMap.put("price", youcanMassProductUpdateRequestDTO.getProductPriceUpdateRate());
        }

        //prepare product update data tracker to write the data in Excel sheet
        YoucanProductUpdateFileDataDTO youcanProductUpdateFileDataDTO = new YoucanProductUpdateFileDataDTO();

        for(YoucanProductDTO youcanProductDTO : youcanProductDTOList){
            //update the product info
            updateProduct(youcanProductDTO, productUpdatesValueMap);

            //record the update happened to this product to be added to Excel sheet
            YoucanProductUpdateDTO youcanProductUpdateDTO = createUpdateTrackingRecordForProduct(youcanProductDTO, youcanMassProductUpdateRequestDTO);
            youcanProductUpdateFileDataDTO.getYoucanProductUpdateDTOList().add(youcanProductUpdateDTO);
        }

        //now, all products are supposed to be updated ... and we have tracked all updated data
        //we need to generate Excel sheet with all updates and save it as a blob file
        HashMap<Long, LinkedList<String>> productsUpdateFileDate = buildYoucanProductUpdateFileData(youcanProductUpdateFileDataDTO);
        Workbook workbook = excelFileHandler.generateExcelWorkbook(productsUpdateFileDate, "youcanProductsUpdate_"+String.valueOf(new Date()));
        //save the workbook in the storage
        //TODO : NEEDS TO BE CHECKED (FILE PATH, FILE NAME)
        this.storageService.saveBlob(excelFileHandler.generateExcelFileByteArray(workbook), youcanProductUpdateFileDataDTO.getFileName());
        //create blob with all file details in the DB
    }

    /**
     * create a product update tracking info object that is used to be added to a user downloadable Excel sheet
     * @param youcanProductDTO
     * @param youcanMassProductUpdateRequestDTO
     * @return
     */
    public YoucanProductUpdateDTO createUpdateTrackingRecordForProduct(YoucanProductDTO youcanProductDTO, YoucanMassProductUpdateRequestDTO youcanMassProductUpdateRequestDTO){
        YoucanProductUpdateDTO youcanProductUpdateDTO = new YoucanProductUpdateDTO();
        youcanProductUpdateDTO.setProductCode(youcanProductDTO.getId());
        youcanProductUpdateDTO.setProductEnglishName(youcanProductDTO.getName());
        if(youcanMassProductUpdateRequestDTO.isUpdateVisibility()){
            youcanProductUpdateDTO.setIsVisibilityUpdated("true");
        }else{
            youcanProductUpdateDTO.setIsVisibilityUpdated("false");
        }
        if(youcanMassProductUpdateRequestDTO.isUpdatePrice()){
            youcanProductUpdateDTO.setIsPriceUpdated("true");
        }else{
            youcanProductUpdateDTO.setIsPriceUpdated("false");
        }
        youcanProductUpdateDTO.setPriceValueBefore(String.valueOf(youcanProductDTO.getPrice()));
        youcanProductUpdateDTO.setPriceValueAfter(String.valueOf(youcanProductDTO.getPrice() + (youcanProductDTO.getPrice() * youcanMassProductUpdateRequestDTO.getProductPriceUpdateRate()/100)));
        youcanProductUpdateDTO.setUpdateTimestamp(String.valueOf(new Date()));
        return youcanProductUpdateDTO;
    }

    /**
     * used to fill the Excel workbook file with updated products data
     * this data is used to track all the updated that happens through REST APIs and synchronizer
     * @param youcanProductUpdateFileDataDTO
     * @return HashMap<Long, LinkedList<String>>
     */
    public HashMap<Long, LinkedList<String>> buildYoucanProductUpdateFileData(YoucanProductUpdateFileDataDTO youcanProductUpdateFileDataDTO){
        HashMap<Long, LinkedList<String>> dataHashMap = new HashMap<>();
        //add headers
        LinkedList<String> headers = new LinkedList<>(youcanProductUpdateFileDataDTO.getFileHeaders());
        dataHashMap.put(0L, headers);

        //add data values
        long dataCount = 0;
        for(YoucanProductUpdateDTO youcanProductUpdateDTO : youcanProductUpdateFileDataDTO.getYoucanProductUpdateDTOList()){
            LinkedList<String> dataValues = new LinkedList<>();
            dataValues.add(youcanProductUpdateDTO.getProductCode());
            dataValues.add(youcanProductUpdateDTO.getProductEnglishName());
            dataValues.add(youcanProductUpdateDTO.getIsVisibilityUpdated());
            dataValues.add(youcanProductUpdateDTO.getIsPriceUpdated());
            dataValues.add(youcanProductUpdateDTO.getPriceValueBefore());
            dataValues.add(youcanProductUpdateDTO.getPriceValueBefore());
            dataValues.add(youcanProductUpdateDTO.getUpdateTimestamp());
            dataHashMap.put(dataCount, dataValues);
        }
        return dataHashMap;
    }
}
