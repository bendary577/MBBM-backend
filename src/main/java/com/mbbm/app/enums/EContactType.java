package com.mbbm.app.enums;

import java.util.Map;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toMap;

public enum EContactType {

    MOBILE(1),
    HOME_PHONE(2),
    EMAIL(3);

    public final int value;
    private final static Map<Integer, EContactType> contactTypeMap =
            stream(EContactType.values()).collect(toMap(contactType -> contactType.value, contactType -> contactType));

    private EContactType(int value) {
        this.value = value;
    }

    public static EContactType valueOf(int genderValue) {
        return contactTypeMap.get(genderValue);
    }
}
