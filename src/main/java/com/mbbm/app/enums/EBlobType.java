package com.mbbm.app.enums;

import java.util.Map;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toMap;

public enum EBlobType {
    TXT(0),
    PDF(1),
    JPG(2),
    PNG(3),
    XLSX(4),
    DOCX(5),
    MP4(6),
    MP3(7),
    CSV(8);

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
