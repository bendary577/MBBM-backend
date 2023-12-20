package com.mbbm.app.enums;

import java.util.Map;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toMap;

public enum EGender {
    UNSET(0),
    MALE(1),
    FEMALE(2);

    public final int value;

    private final static Map<Integer, EGender> genderMap =
            stream(EGender.values()).collect(toMap(gender -> gender.value, gender -> gender));

    private EGender(int value) {
        this.value = value;
    }

    public static EGender valueOf(int genderValue) {
        return genderMap.get(genderValue);
    }
}
