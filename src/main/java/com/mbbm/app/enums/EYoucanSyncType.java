package com.mbbm.app.enums;

import java.util.Map;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toMap;

public enum EYoucanSyncType {

    GET(1),
    GET_UPDATE(2);

    public final int value;
    private final static Map<Integer, EYoucanSyncType> syncTypeMap =
            stream(EYoucanSyncType.values()).collect(toMap(type -> type.value, type -> type));

    private EYoucanSyncType(int value) {
        this.value = value;
    }
}
