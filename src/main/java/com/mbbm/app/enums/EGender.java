package com.mbbm.app.enums;

public enum EGender {
    UNSET(0),
    MALE(1),
    FEMALE(2);

    public final int value;

    private EGender(int value) {
        this.value = value;
    }
}
