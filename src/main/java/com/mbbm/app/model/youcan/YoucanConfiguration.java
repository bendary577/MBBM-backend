package com.mbbm.app.model.youcan;

import com.mbbm.app.enums.ESyncStatus;
import com.mbbm.app.enums.EYoucanPricingStrategy;
import com.mbbm.app.enums.EYoucanSyncType;
import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Duration;


/**
 * @author mohamed.bendary
 * */
@Entity
@Table(name = "youcan_configuration")
public class YoucanConfiguration implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column(name = "sync_products")
	private boolean syncProducts;

    @Column(name = "sync_duration_in_seconds")
    private Long  syncDurationInSeconds;

    @Column(name = "last_sync_time", columnDefinition = "TIMESTAMP")
    private Timestamp lastSyncTime;

    @Column(name = "last_sync_status")
    private ESyncStatus lastSyncStatus;

    @Column(name = "sync_type")
    private EYoucanSyncType syncType;

    @Column(name = "update_prices")
    private boolean updatePrices;

    @Column(name = "update_visibility")
    private boolean updateVisibility;

    @Column(name = "pricing_strategy")
    private EYoucanPricingStrategy pricingStrategy;

    /***
     * fixed rate of price increase for all products (fixed amount)
     * E.I. product with cost price = 200, pricing rate = 5, updated price will be 205
     */
    @Column(name = "general_pricing_rate")
    private int generalPricingRate;

    /***
     * fixed rate of price increase per category (fixed amount)
     * E.I. product with cost price = 200, pricing rate = 5, updated price will be 205
     */
    @Column(name = "category_pricing_rate")
    private int categoryPricingRate;

    /***
     * rate of price increase in percentage per product (out of cost price)
     * E.I. product with cost price = 200, pricing rate = 5, updated price will be 210
     */
    @Column(name = "product_pricing_rate")
    private int productPricingRate;

    @OneToOne
    @JoinColumn(name = "integration_id")
    private YoucanIntegration integration;


    public YoucanConfiguration(){}


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isSyncProducts() {
        return syncProducts;
    }

    public void setSyncProducts(boolean syncProducts) {
        this.syncProducts = syncProducts;
    }

    public boolean isUpdatePrices() {
        return updatePrices;
    }

    public void setUpdatePrices(boolean updatePrices) {
        this.updatePrices = updatePrices;
    }

    public boolean isUpdateVisibility() {
        return updateVisibility;
    }

    public void setUpdateVisibility(boolean updateVisibility) {
        this.updateVisibility = updateVisibility;
    }

    public EYoucanPricingStrategy getPricingStrategy() {
        return pricingStrategy;
    }

    public void setPricingStrategy(EYoucanPricingStrategy pricingStrategy) {
        this.pricingStrategy = pricingStrategy;
    }

    public int getGeneralPricingRate() {
        return generalPricingRate;
    }

    public void setGeneralPricingRate(int generalPricingRate) {
        this.generalPricingRate = generalPricingRate;
    }

    public int getCategoryPricingRate() {
        return categoryPricingRate;
    }

    public void setCategoryPricingRate(int categoryPricingRate) {
        this.categoryPricingRate = categoryPricingRate;
    }

    public int getProductPricingRate() {
        return productPricingRate;
    }

    public void setProductPricingRate(int productPricingRate) {
        this.productPricingRate = productPricingRate;
    }

    public YoucanIntegration getIntegration() {
        return integration;
    }

    public void setIntegration(YoucanIntegration integration) {
        this.integration = integration;
    }

    public void setSyncDurationInSeconds(Duration duration) {
        this.syncDurationInSeconds = duration.getSeconds();
    }

    public Duration getSyncDurationInSeconds() {
        return Duration.ofSeconds(syncDurationInSeconds);
    }

    public EYoucanSyncType getSyncType() {
        return syncType;
    }

    public void setSyncType(EYoucanSyncType syncType) {
        this.syncType = syncType;
    }

    public Timestamp getLastSyncTime() {
        return lastSyncTime;
    }

    public void setLastSyncTime(Timestamp lastSyncTime) {
        this.lastSyncTime = lastSyncTime;
    }

    public ESyncStatus getLastSyncStatus() {
        return lastSyncStatus;
    }

    public void setLastSyncStatus(ESyncStatus lastSyncStatus) {
        this.lastSyncStatus = lastSyncStatus;
    }
}
