package com.mbbm.app.whatsapp.services;

import com.mbbm.app.whatsapp.credentials.WhatsappCredentials;
import okhttp3.*;
import org.springframework.stereotype.Service;
import java.io.File;
import java.net.URL;
import java.nio.file.Paths;

@Service
public class WhatsappMediaService {


    public String uploadMediaToWhatsappAPI(String whatsappBusinessPhoneNumber){
        Response response = null;
        try {
            String whatsappUploadMediaURL = WhatsappCredentials.WHATSAPP_BASE_API_URL + whatsappBusinessPhoneNumber + "/media";
            HttpUrl.Builder httpBuilder = HttpUrl.parse(whatsappUploadMediaURL).newBuilder();

            //get media path
            URL res = getClass().getClassLoader().getResource("MBBM-Logo.jpg");
            File file = Paths.get(res.toURI()).toFile();
            String absolutePath = file.getAbsolutePath();

            //media file properties
            final MediaType MEDIA_TYPE = MediaType.parse("image/jpg");

            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("file", file.getName(), RequestBody.create(MEDIA_TYPE, file))
                    .addFormDataPart("type", MEDIA_TYPE.toString())
                    .addFormDataPart("messaging_product", "whatsapp")
                    .build();

            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(httpBuilder.build())
                    .method("POST", requestBody)
                    .addHeader("Authorization", WhatsappCredentials.WHATSAPP_API_ACCESS_TOKEN)
                    .build();

            response = client.newCall(request).execute();

            if (response.code() == 200) {
            } else {
            }
        }catch(Exception exception){

        }
        return response.message();
    }



}