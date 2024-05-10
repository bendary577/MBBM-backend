package com.mbbm.app.enums;

import java.util.Map;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toMap;

public enum ESubscriptionType {
    MONTHLY(0),
    YEARLY(1),
    ONE_TIME_PAYMENT(2);

    public final int value;

    private final static Map<Integer, ESubscriptionType> subscriptionTypeMap =
            stream(ESubscriptionType.values()).collect(toMap(subscriptionType -> subscriptionType.value, subscriptionType -> subscriptionType));


    private ESubscriptionType(int value) {
        this.value = value;
    }

    public static ESubscriptionType valueOf(int subscriptionTypeValue) {
        return subscriptionTypeMap.get(subscriptionTypeValue);
    }
}
