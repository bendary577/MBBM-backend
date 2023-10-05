package com.mbbm.app.enums;

public enum EContactType {

    MOBILE(1),
    HOME_PHONE(2),
    EMAIL(3);

    public final int value;

    private EContactType(int value) {
        this.value = value;
    }
}
