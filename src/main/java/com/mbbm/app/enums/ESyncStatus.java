package com.mbbm.app.enums;

import java.util.Map;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toMap;

public enum ESyncStatus {

    FAIL(0),
    SUCCESS(1);


    public final int value;
    private final static Map<Integer, ESyncStatus> syncStatusMap =
            stream(ESyncStatus.values()).collect(toMap(status -> status.value, status -> status));

    private ESyncStatus(int value) {
        this.value = value;
    }

    public static ESyncStatus valueOf(int genderValue) {
        return syncStatusMap.get(genderValue);
    }
}
