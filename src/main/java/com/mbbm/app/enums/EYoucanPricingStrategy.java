package com.mbbm.app.enums;

public enum EYoucanPricingStrategy {

    RATE_FOR_ALL(1),
    RATE_PER_CATEGORY(2),
    RATE_PER_PRODUCT(3);

    public final int value;

    private EYoucanPricingStrategy(int value) {
        this.value = value;
    }
}
