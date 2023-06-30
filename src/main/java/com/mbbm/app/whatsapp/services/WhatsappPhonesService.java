package com.mbbm.app.whatsapp.services;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mbbm.app.whatsapp.credentials.WhatsappCredentials;
import com.mbbm.app.whatsapp.phones.BusinessAccountPhonesResponse;
import com.mbbm.app.whatsapp.phones.PhoneResponseObject;
import com.squareup.okhttp.*;
import org.springframework.stereotype.Service;

import java.io.File;
import java.net.URL;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WhatsappPhonesService {

    public List<PhoneResponseObject> getWhatsappBusinessAccountPhones(){
        Response response = null;
        List<PhoneResponseObject> responsePhonesList = null;
        try {
            Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
            HttpUrl.Builder httpBuilder = HttpUrl.parse(WhatsappCredentials.WHATSAPP_PHONES_API_URL).newBuilder();

            //add query params to request
            Map<String,String> params = new HashMap<>();
            params.put("access_token", WhatsappCredentials.WHATSAPP_API_ACCESS_TOKEN_NO_BEARER);

            for(Map.Entry<String, String> param : params.entrySet()) {
                httpBuilder.addQueryParameter(param.getKey(),param.getValue());
            }

            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(httpBuilder.build())
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Accept", "application/json")
                    .addHeader("Authorization", WhatsappCredentials.WHATSAPP_API_ACCESS_TOKEN_NO_BEARER)
                    .build();

            response = client.newCall(request).execute();

            BusinessAccountPhonesResponse businessAccountPhonesResponse = gson.fromJson(response.body().string(), BusinessAccountPhonesResponse.class);

            if (response.code() == 200) {
                responsePhonesList = businessAccountPhonesResponse.getData();
            }
        }catch(Exception exception){}
        return responsePhonesList;
    }

}
