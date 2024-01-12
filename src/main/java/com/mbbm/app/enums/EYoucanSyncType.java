package com.mbbm.app.enums;

public enum EYoucanSyncType {

    GET(1),
    GET_UPDATE(2);

    public final int value;

    private EYoucanSyncType(int value) {
        this.value = value;
    }
}
