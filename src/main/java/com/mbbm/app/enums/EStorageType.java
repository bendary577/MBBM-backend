package com.mbbm.app.enums;

import java.util.Map;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toMap;

public enum EStorageType {
    CLOUD(0),
    DISK(1);

    public final int value;

    private final static Map<Integer, EStorageType> storageTypeMap =
            stream(EStorageType.values()).collect(toMap(storageType -> storageType.value, storageType -> storageType));


    private EStorageType(int value) {
        this.value = value;
    }

    public static EStorageType valueOf(int storageTypeValue) {
        return storageTypeMap.get(storageTypeValue);
    }
}
