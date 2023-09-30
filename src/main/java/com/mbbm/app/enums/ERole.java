package com.mbbm.app.enums;

public enum ERole {
    ROLE_USER("User"),
    ROLE_MODERATOR("Moderator"),
    ROLE_ADMIN("Admin"),
    ROLE_MEDIA_BUYER("MediaBuyer");

    public final String value;

    private ERole(String value) {
        this.value = value;
    }

    public static ERole getRoleByName(String userRoleName){
        switch(userRoleName){
            case "User":
                return ROLE_USER;
            case "Moderator":
                return ROLE_MODERATOR;
            case "Admin":
                return ROLE_ADMIN;
            case "MediaBuyer":
                return ROLE_MEDIA_BUYER;
            default:
                //TODO : handle this situation properly (throw exception)
                return null;
        }
    }
}
