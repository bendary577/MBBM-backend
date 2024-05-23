package com.mbbm.app.http.request.youcan;

public class YoucanMassProductUpdateRequestDTO {

    boolean updateVisibility;
    boolean updatePrice;
    int productPriceUpdatePercentage;
    /**
     * will the productPriceUpdatePercentage be added to the price ? or deducted from the price
     */
    boolean pricePercentIncrease;

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

    public int getProductPriceUpdatePercentage() {
        return productPriceUpdatePercentage;
    }

    public void setProductPriceUpdatePercentage(int productPriceUpdatePercentage) {
        this.productPriceUpdatePercentage = productPriceUpdatePercentage;
    }

    public boolean isPricePercentIncrease() {
        return pricePercentIncrease;
    }

    public void setPricePercentIncrease(boolean pricePercentIncrease) {
        this.pricePercentIncrease = pricePercentIncrease;
    }
}
