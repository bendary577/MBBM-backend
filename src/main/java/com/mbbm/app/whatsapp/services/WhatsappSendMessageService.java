package com.mbbm.app.whatsapp.services;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mbbm.app.enums.EWhatsappMessageType;
import com.mbbm.app.whatsapp.messages.*;
import com.mbbm.app.whatsapp.credentials.WhatsappCredentials;
import com.squareup.okhttp.*;
import org.springframework.stereotype.Service;

@Service
public class WhatsappSendMessageService {


    public String sendWhatsappMessageTemplate(){
        Response response = null;
        try {
            OkHttpClient client = new OkHttpClient();
            Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();

            MessageLanguage language = new MessageLanguage();
            MessageTemplate template = new MessageTemplate();
            WhatsappMessage whatsappMessage = new WhatsappMessage();
            language.setCode("en_US");
            template.setName("hello_world");
            template.setLanguage(language);
            whatsappMessage.setMessaging_product("whatsapp");
            whatsappMessage.setTo("201099482906");
            whatsappMessage.setType(EWhatsappMessageType.MESSAGE_TEMPLATE.value);
            whatsappMessage.setTemplate(template);

            String jsonOrder = gson.toJson(whatsappMessage);

            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, jsonOrder);
            Request request = new Request.Builder()
                    .url(WhatsappCredentials.WHATSAPP_MESSAGE_API_URL)
                    .method("POST", body)
                    .addHeader("Authorization", WhatsappCredentials.WHATSAPP_API_ACCESS_TOKEN)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Accept", "application/json")
                    .build();

            response = client.newCall(request).execute();

            if (response.code() == 200) {} else {}

        } catch (Exception e) {
            e.printStackTrace();
        }
        return response.message();
    }

    public String sendWhatsappTextMessage(){
        Response response = null;
        try {
            OkHttpClient client = new OkHttpClient();
            Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();

            Text text = new Text();
            text.setPreview_url(false);
            text.setBody("text message");

            WhatsappMessage whatsappMessage = new WhatsappMessage();
            whatsappMessage.setMessaging_product("whatsapp");
            whatsappMessage.setTo("201099482906");
            whatsappMessage.setType(EWhatsappMessageType.MESSAGE_TEXT.value);
            whatsappMessage.setText(text);

            String jsonOrder = gson.toJson(whatsappMessage);

            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, jsonOrder);
            Request request = new Request.Builder()
                    .url(WhatsappCredentials.WHATSAPP_MESSAGE_API_URL)
                    .method("POST", body)
                    .addHeader("Authorization", WhatsappCredentials.WHATSAPP_API_ACCESS_TOKEN)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Accept", "application/json")
                    .build();

            response = client.newCall(request).execute();

            if (response.code() == 200) {} else {}

        } catch (Exception e) {
            e.printStackTrace();
        }
        return response.message();
    }

    public String sendWhatsappMessageWithImage(){
        Response response = null;
        try {
            OkHttpClient client = new OkHttpClient();
            Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();

            Image image = new Image();
            image.setLink("https://imgur.com/a/wo6ZM5T");

            WhatsappMessage whatsappMessage = new WhatsappMessage();
            whatsappMessage.setMessaging_product("whatsapp");
            whatsappMessage.setTo("201099482906");
            whatsappMessage.setType(EWhatsappMessageType.MESSAGE_IMAGE.value);
            whatsappMessage.setImage(image);

            String jsonOrder = gson.toJson(whatsappMessage);

            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, jsonOrder);
            Request request = new Request.Builder()
                    .url(WhatsappCredentials.WHATSAPP_MESSAGE_API_URL)
                    .method("POST", body)
                    .addHeader("Authorization", WhatsappCredentials.WHATSAPP_API_ACCESS_TOKEN)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Accept", "application/json")
                    .build();

            response = client.newCall(request).execute();

            if (response.code() == 200) {} else {}

        } catch (Exception e) {
            e.printStackTrace();
        }
        return response.message();
    }

}