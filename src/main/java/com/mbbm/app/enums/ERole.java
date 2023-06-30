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
}
