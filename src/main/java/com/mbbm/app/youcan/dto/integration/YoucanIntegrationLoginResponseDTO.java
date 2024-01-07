package com.mbbm.app.youcan.dto.integration;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties({"token", "token_type", "is_staff", "expired_at"})
public class YoucanIntegrationLoginResponseDTO {

    private String token;
    private String token_type;
    private String is_staff;
    private String expired_at;

    private List<YoucanStoreDTO> stores;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTokenType() {
        return token_type;
    }

    public void setTokenType(String tokenType) {
        this.token_type = tokenType;
    }

    public String getIs_staff() {
        return is_staff;
    }

    public void setIs_staff(String is_staff) {
        this.is_staff = is_staff;
    }

    public String getExpired_at() {
        return expired_at;
    }

    public void setExpired_at(String expired_at) {
        this.expired_at = expired_at;
    }

    public List<YoucanStoreDTO> getStores() {
        return stores;
    }

    public void setStores(List<YoucanStoreDTO> stores) {
        this.stores = stores;
    }
}
