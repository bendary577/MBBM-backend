package com.mbbm.app.model.youcan;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mbbm.app.model.base.Link;
import com.mbbm.app.model.base.Profile;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;


/**
 * @author mohamed.bendary
 * */
@Entity
@Table(name = "youcan_integration")
public class YoucanIntegration implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column(name = "token", columnDefinition = "longtext")
	private String token;

    @Column(name = "expiredAt")
    private String expiredAt;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<YouCanStore> youCanStores;

    @OneToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @OneToOne(mappedBy = "integration", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private YoucanConfiguration youcanConfiguration;


    public YoucanIntegration(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getExpiredAt() {
        return expiredAt;
    }

    public void setExpiredAt(String expiredAt) {
        this.expiredAt = expiredAt;
    }

    public Set<YouCanStore> getYouCanStores() {
        return youCanStores;
    }

    public void setYouCanStores(Set<YouCanStore> youCanStores) {
        this.youCanStores = youCanStores;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public YoucanConfiguration getYoucanConfiguration() {
        return youcanConfiguration;
    }

    public void setYoucanConfiguration(YoucanConfiguration youcanConfiguration) {
        this.youcanConfiguration = youcanConfiguration;
    }
}
