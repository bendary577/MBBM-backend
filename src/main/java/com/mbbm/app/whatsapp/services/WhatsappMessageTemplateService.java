package com.mbbm.app.whatsapp.services;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mbbm.app.whatsapp.credentials.WhatsappCredentials;
import com.mbbm.app.whatsapp.messageTemplates.GetMessageTemplatesResponse;
import com.mbbm.app.whatsapp.messageTemplates.MessageTemplatesResponse;
import com.mbbm.app.whatsapp.messageTemplates.MessageTextObject;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WhatsappMessageTemplateService {

    //TO DO :: NEEDS TO BE TESTED :: RETURNS UNAUTHENTICATED
    public String createMessageTemplate(){
        Response response = null;
        try {
            Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
            HttpUrl.Builder httpBuilder = HttpUrl.parse(WhatsappCredentials.WHATSAPP_MESSAGE_TEMPLATE_API_URL).newBuilder();

            //create components array
            List<MessageTextObject> componentsList = new ArrayList<>();

            MessageTextObject messageTextObject = new MessageTextObject();
            messageTextObject.setType("BODY");
            messageTextObject.setText("hello mbbm !!");

            componentsList.add(messageTextObject);
            String componentsValueJsonObject = gson.toJson(componentsList);

            //add query params to request
            Map<String,String> params = new HashMap<>();
            params.put("access_token", WhatsappCredentials.WHATSAPP_API_ACCESS_TOKEN_NO_BEARER);
            params.put("components", componentsValueJsonObject);
            params.put("category", "UTILITY");
            params.put("language", "en_US");
            params.put("name", "mbbm_test_message_2");

            for(Map.Entry<String, String> param : params.entrySet()) {
                httpBuilder.addQueryParameter(param.getKey(),param.getValue());
            }

            OkHttpClient client = new OkHttpClient();

            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, "");

            Request request = new Request.Builder()
                    .url(httpBuilder.build())
                    .method("POST", body)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Accept", "application/json")
                    .build();

            response = client.newCall(request).execute();

            if (response.code() == 200) {
            } else {
            }
        }catch(Exception exception){

        }
        return response.message();
    }

    public List<MessageTemplatesResponse> getMessageTemplates(){
        Response response = null;
        List<MessageTemplatesResponse> messageTemplatesResponseList = null;
        try {
            Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
            HttpUrl.Builder httpBuilder = HttpUrl.parse(WhatsappCredentials.WHATSAPP_MESSAGE_TEMPLATE_API_URL).newBuilder();

            //add query params to request
            Map<String,String> params = new HashMap<>();
            params.put("access_token", WhatsappCredentials.WHATSAPP_API_ACCESS_TOKEN_NO_BEARER);
            params.put("limit", "3");

            for(Map.Entry<String, String> param : params.entrySet()) {
                httpBuilder.addQueryParameter(param.getKey(),param.getValue());
            }

            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(httpBuilder.build())
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Accept", "application/json")
                    .build();

            response = client.newCall(request).execute();

            GetMessageTemplatesResponse businessAccountPhonesResponse = gson.fromJson(response.body().string(), GetMessageTemplatesResponse.class);

            if (response.code() == 200) {
                messageTemplatesResponseList = businessAccountPhonesResponse.getData();
            }
        }catch(Exception exception){}
        return messageTemplatesResponseList;
    }

}