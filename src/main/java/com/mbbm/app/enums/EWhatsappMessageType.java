package com.mbbm.app.enums;

public enum EWhatsappMessageType {
    MESSAGE_TEMPLATE("template"),
    MESSAGE_TEXT("text"),
    MESSAGE_IMAGE("image");

    public final String value;

    private EWhatsappMessageType(String value) {
        this.value = value;
    }
}
