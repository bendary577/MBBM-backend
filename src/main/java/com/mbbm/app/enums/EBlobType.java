package com.mbbm.app.enums;

import java.util.Map;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toMap;

public enum EBlobType {
    AVATAR(0),
    YOUCAN_PRODUCT_UPDATE_TRACKING_FILE(1);

    public final int value;

    private final static Map<Integer, EBlobType> blobTypeMap =
            stream(EBlobType.values()).collect(toMap(blobType -> blobType.value, blobType -> blobType));


    private EBlobType(int value) {
        this.value = value;
    }

    public static EBlobType valueOf(int blobTypeValue) {
        return blobTypeMap.get(blobTypeValue);
    }
}
