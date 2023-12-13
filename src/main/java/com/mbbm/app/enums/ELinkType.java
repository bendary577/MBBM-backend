package com.mbbm.app.enums;

public enum ELinkType {

    SOCIAL_MEDIA_PROFILE(1), //facebook, twitter, linkedin
    PERSONAL_WEBSITE(2),
    EXTERNAL_WEBSITE(3), //github, behance
    BLOG(4);

    public final int value;

    private ELinkType(int value) {
        this.value = value;
    }
}
