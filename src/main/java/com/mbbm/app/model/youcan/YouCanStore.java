package com.mbbm.app.model.youcan;

import com.mbbm.app.model.base.Profile;

import javax.persistence.*;
import java.io.Serializable;


/**
 * @author mohamed.bendary
 * */
@Entity
@Table(name = "youcan_store")
public class YouCanStore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "storeId")
    private String storeId;

    @Column(name = "slug")
    private String slug;

    @Column(name = "isActive")
    private boolean isActive;

    @Column(name = "isEmailVerified")
    private boolean isEmailVerified;

    @ManyToOne
    @JoinColumn(name = "integration_id")
    private YoucanIntegration youcanIntegration;

    public YouCanStore(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isEmailVerified() {
        return isEmailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        isEmailVerified = emailVerified;
    }

    public YoucanIntegration getYoucanIntegration() {
        return youcanIntegration;
    }

    public void setYoucanIntegration(YoucanIntegration youcanIntegration) {
        this.youcanIntegration = youcanIntegration;
    }
}
