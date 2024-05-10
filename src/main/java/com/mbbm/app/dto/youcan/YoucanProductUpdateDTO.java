package com.mbbm.app.dto.youcan;

public class YoucanProductUpdateDTO {

    private String productCode;
    private String productEnglishName;
    private String isVisibilityUpdated;
    private String isPriceUpdated;
    private String priceValueBefore;
    private String priceValueAfter;
    private String updateTimestamp;

    public YoucanProductUpdateDTO(){}

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductEnglishName() {
        return productEnglishName;
    }

    public void setProductEnglishName(String productEnglishName) {
        this.productEnglishName = productEnglishName;
    }

    public String getIsVisibilityUpdated() {
        return isVisibilityUpdated;
    }

    public void setIsVisibilityUpdated(String isVisibilityUpdated) {
        this.isVisibilityUpdated = isVisibilityUpdated;
    }

    public String getIsPriceUpdated() {
        return isPriceUpdated;
    }

    public void setIsPriceUpdated(String isPriceUpdated) {
        this.isPriceUpdated = isPriceUpdated;
    }

    public String getPriceValueBefore() {
        return priceValueBefore;
    }

    public void setPriceValueBefore(String priceValueBefore) {
        this.priceValueBefore = priceValueBefore;
    }

    public String getPriceValueAfter() {
        return priceValueAfter;
    }

    public void setPriceValueAfter(String priceValueAfter) {
        this.priceValueAfter = priceValueAfter;
    }

    public String getUpdateTimestamp() {
        return updateTimestamp;
    }

    public void setUpdateTimestamp(String updateTimestamp) {
        this.updateTimestamp = updateTimestamp;
    }
}

