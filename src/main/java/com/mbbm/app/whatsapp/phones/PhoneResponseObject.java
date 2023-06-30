package com.mbbm.app.whatsapp.phones;

public class PhoneResponseObject {

    private String verified_name;
    private String code_verification_status;
    private String display_phone_number;
    private String quality_rating;
    private String id;

    public String getVerified_name() {
        return verified_name;
    }

    public void setVerified_name(String verified_name) {
        this.verified_name = verified_name;
    }

    public String getCode_verification_status() {
        return code_verification_status;
    }

    public void setCode_verification_status(String code_verification_status) {
        this.code_verification_status = code_verification_status;
    }

    public String getDisplay_phone_number() {
        return display_phone_number;
    }

    public void setDisplay_phone_number(String display_phone_number) {
        this.display_phone_number = display_phone_number;
    }

    public String getQuality_rating() {
        return quality_rating;
    }

    public void setQuality_rating(String quality_rating) {
        this.quality_rating = quality_rating;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
