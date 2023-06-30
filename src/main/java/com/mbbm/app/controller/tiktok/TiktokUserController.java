package com.mbbm.app.controller.tiktok;

import com.mbbm.app.tiktok.users.TiktokRedirectResponse;
import com.mbbm.app.tiktok.users.TiktokUsersCredentials;
import com.squareup.okhttp.*;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/tiktok/users/")
public class TiktokUserController {

    @GetMapping("/authorize")
    public void authorize() throws Exception {

        //prevent request forgery attacks
        String csrfState = String.valueOf(Math.random()).substring(2);
        OkHttpClient client = new OkHttpClient();
        Cookie csrfCookie =new Cookie("csrfState",csrfState);
        csrfCookie.setMaxAge(60000 );

        //build tiktok url
        String tiktokAuthURL = TiktokUsersCredentials.TIKTOK_AUTH_ENDPOINT +
                "?client_key=" +
                "{" + TiktokUsersCredentials.CLIENT_KEY + "}" +
                "&scope=user.info.basic,video.list" +
                "&response_type=code" +
                "&redirect_uri=" +
                "{" + TiktokUsersCredentials.MBBM_TIKTOK_AUTH_REDIRECT_ENDPOINT + "}" +
                "&state=" +
                csrfState;

        Request request = new Request.Builder()
                .url(tiktokAuthURL)
                .addHeader("Cookie", String.valueOf(csrfCookie))
                .build();

        client.newCall(request).execute();
    }

    @GetMapping("/authorize_redirect")
    public void authorizeRedirect(@RequestParam TiktokRedirectResponse tiktokRedirectResponse) {
        String code = tiktokRedirectResponse.getCode();
        System.out.println(code);
    }
}
