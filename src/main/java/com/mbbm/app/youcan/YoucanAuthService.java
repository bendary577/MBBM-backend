package com.mbbm.app.youcan;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class YoucanAuthService {

    @Autowired
    private YoucanSettings youcanConfigurations;

    private static String YOUCAN_URL = "";

    public YoucanAuthService(){}
    public String login(YoucanLoginRequestDTO youcanLoginRequestDTO){
        Response response = null;
        OkHttpClient client = new OkHttpClient();
        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();

        try {
            YOUCAN_URL = youcanConfigurations.YOUCAN_BASE_API_URL + "/auth/login";
            HttpUrl.Builder httpBuilder = HttpUrl.parse(YOUCAN_URL).newBuilder();
            String jsonOrder = gson.toJson(youcanLoginRequestDTO);

            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, jsonOrder);

            Request request = new Request.Builder()
                    .url(httpBuilder.build())
                    .method("POST", body)
                    .addHeader("Accept", "application/json")
                    .build();

            response = client.newCall(request).execute();

            if (response.code() == 200) {
            } else {}
        }catch(Exception exception){

        }
        return response.message();
    }
}
