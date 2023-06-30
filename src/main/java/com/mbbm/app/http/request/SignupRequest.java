package com.mbbm.app.http.request;

import com.mbbm.app.enums.ERole;

import java.io.Serializable;

public class SignupRequest implements Serializable {

    private static final long serialVersionUID = 5926468583005150707L;

    private String name;
    private String username;
    private String email;
    private String password;
    private String address;
    private String phone;
    private String domain;
    private ERole userType;

    //need default constructor for JSON Parsing
    public SignupRequest() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public ERole getUserType() {
        return userType;
    }

    public void setUserType(ERole userType) {
        this.userType = userType;
    }
}
