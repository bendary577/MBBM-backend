package com.mbbm.app.model.youcan;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author mohamed.bendary
 * */
@Entity
@Table(name = "youcan_product_update")
public class YoucanProductUpdate implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column(name = "product_code")
	private String productCode;

    @Column(name = "last_update_time", columnDefinition = "TIMESTAMP")
    private Timestamp lastUpdateTime;

    @Column(name = "timestamp", columnDefinition = "TIMESTAMP")
    private Timestamp timestamp;

    @OneToOne
    @JoinColumn(name = "store_id")
    private YouCanStore youCanStore;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Timestamp getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Timestamp lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public YouCanStore getYouCanStore() {
        return youCanStore;
    }

    public void setYouCanStore(YouCanStore youCanStore) {
        this.youCanStore = youCanStore;
    }
}
