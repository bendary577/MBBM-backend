package com.mbbm.app.http.request.youcan;

public class YoucanMassProductUpdateRequestDTO {

    boolean updateVisibility;
    boolean updatePrice;
    int productPriceUpdateRate;

    public YoucanMassProductUpdateRequestDTO(){}

    public boolean isUpdateVisibility() {
        return updateVisibility;
    }

    public void setUpdateVisibility(boolean updateVisibility) {
        this.updateVisibility = updateVisibility;
    }

    public boolean isUpdatePrice() {
        return updatePrice;
    }

    public void setUpdatePrice(boolean updatePrice) {
        this.updatePrice = updatePrice;
    }

    public int getProductPriceUpdateRate() {
        return productPriceUpdateRate;
    }

    public void setProductPriceUpdateRate(int productPriceUpdateRate) {
        this.productPriceUpdateRate = productPriceUpdateRate;
    }
}
