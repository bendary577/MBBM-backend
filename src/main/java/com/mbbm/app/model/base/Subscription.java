package com.mbbm.app.model.base;

import com.mbbm.app.enums.EStorageType;
import com.mbbm.app.enums.ESubscriptionType;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;


/**
 * @author mohamed.bendary
 * the base Subscription model definition in the system
 * */
@Entity
@Table(name = "subscription")
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column(name = "subscription_type")
	@NotNull(message = "subscription type is mandatory")
	private ESubscriptionType name;

	@Column(name = "price")
//	@NotBlank(message = "price is mandatory")
	private Double price;

    @Column(name = "storage_type")
    @NotNull(message = "storage type is mandatory")
    private EStorageType storageType;

	@OneToMany(mappedBy = "subscription", cascade = CascadeType.ALL)
	private List<Profile> profiles;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EStorageType getStorageType() {
		return storageType;
	}

	public void setStorageType(EStorageType storageType) {
		this.storageType = storageType;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public List<Profile> getProfiles() {
		return profiles;
	}

	public void setProfiles(List<Profile> profiles) {
		this.profiles = profiles;
	}

	public ESubscriptionType getName() {
		return name;
	}

	public void setName(ESubscriptionType name) {
		this.name = name;
	}
}
