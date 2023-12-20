package com.mbbm.app.controller.tiktok;

import com.mbbm.app.tiktok.users.TiktokAuthResponse;
import com.mbbm.app.tiktok.users.TiktokRedirectResponse;
import com.mbbm.app.tiktok.users.TiktokUsersCredentials;
import com.squareup.okhttp.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/tiktok/users/")
public class TiktokUserController {

    @GetMapping("/authorize")
    public ResponseEntity<?> authorize() throws Exception {

        //prevent request forgery attacks
        String csrfState = String.valueOf(Math.random()).substring(2);
        OkHttpClient client = new OkHttpClient();
        Cookie csrfCookie =new Cookie("csrfState",csrfState);
        csrfCookie.setMaxAge(60000);

        HttpUrl.Builder urlBuilder = HttpUrl.parse(TiktokUsersCredentials.TIKTOK_AUTH_ENDPOINT).newBuilder();
        urlBuilder.addQueryParameter("client_key", TiktokUsersCredentials.CLIENT_KEY);
        urlBuilder.addQueryParameter("scope", "user.info.basic,video.list");
        urlBuilder.addQueryParameter("response_type", "code");
        urlBuilder.addQueryParameter("redirect_uri", TiktokUsersCredentials.MBBM_TIKTOK_AUTH_REDIRECT_ENDPOINT);
        urlBuilder.addQueryParameter("state", csrfState);

        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .addHeader("Cookie", String.valueOf(csrfCookie))
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .build();

        TiktokAuthResponse tiktokAuthResponse = new TiktokAuthResponse();

        Response response = client.newCall(request).execute();
        if(response.code() == 200){
            tiktokAuthResponse.setMessage("tiktok auth success");
            Request redirectRequest = response.request();
            tiktokAuthResponse.setUrl(redirectRequest.url().toString());
            tiktokAuthResponse.setMethod(redirectRequest.method());
        }else{
            tiktokAuthResponse.setMessage("tiktok auth failed");
        }
        return new ResponseEntity<>(tiktokAuthResponse, HttpStatus.OK);
    }

    @GetMapping("/authorize_redirect")
    public void authorizeRedirect(@RequestParam TiktokRedirectResponse tiktokRedirectResponse) {
        String code = tiktokRedirectResponse.getCode();
        System.out.println(code);
    }
}
