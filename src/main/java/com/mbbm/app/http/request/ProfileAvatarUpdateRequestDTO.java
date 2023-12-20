package com.mbbm.app.http.request;

import java.io.Serializable;

public class ProfileAvatarUpdateRequestDTO implements Serializable {

    private static final long serialVersionUID = 5926463592115193097L;

    private String avatarUrl;
    private String avatarSize;
    private String avatarName;
    private int avatarType;

    public ProfileAvatarUpdateRequestDTO(){
        avatarUrl = "";
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getAvatarSize() {
        return avatarSize;
    }

    public void setAvatarSize(String avatarSize) {
        this.avatarSize = avatarSize;
    }

    public String getAvatarName() {
        return avatarName;
    }

    public void setAvatarName(String avatarName) {
        this.avatarName = avatarName;
    }

    public int getAvatarType() {
        return avatarType;
    }

    public void setAvatarType(int avatarType) {
        this.avatarType = avatarType;
    }
}
