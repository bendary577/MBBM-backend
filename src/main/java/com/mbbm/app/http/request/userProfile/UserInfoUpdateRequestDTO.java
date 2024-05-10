package com.mbbm.app.http.request.userProfile;

import java.io.Serializable;
import java.util.LinkedList;

public class UserInfoUpdateRequestDTO implements Serializable {

    private static final long serialVersionUID = 5926461892005193267L;
    private String name;
    private String username;
    private String email;
    private String about;
    private String avatar_url;
    LinkedList<String> phoneNumbers;

    public UserInfoUpdateRequestDTO(){
        name = "";
        username = "";
        email = "";
        about = "";
        avatar_url = "";
        phoneNumbers = new LinkedList<>();
    }

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

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public LinkedList<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(LinkedList<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }
}
