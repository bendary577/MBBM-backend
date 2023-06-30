package com.mbbm.app.http.response;

import com.mbbm.app.http.response.messages.ResponseMessage;
import com.mbbm.app.model.base.Role;

import java.io.Serializable;
import java.util.List;

public class LoginResponse extends ResponseMessage implements Serializable {

    private String userId = "";
    private String userName = "";
    private String email = "";
    private List<Role> roles = null;

    public LoginResponse(){}

    public LoginResponse(String message, String data){
        super(message, data);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List roles) {
        this.roles = roles;
    }

}
