package com.mbbm.app.model.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mbbm.app.enums.ERole;
import com.mbbm.app.model.youcan.YoucanIntegration;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


/**
 * @author mohamed.bendary
 * */
@Entity
@Table(name = "profile")
public class Profile implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column(name = "title")
	private String title;

    @Column(name = "about")
    private String about;

	/***
	 * used for external integration
	 */
	@Column(name = "identifier")
	private UUID identifier;

	@Column(name = "type")
	private ERole type;

	@Column(name = "isActivated")
	private boolean isActivated;

	@Column(name = "isBlocked")
	private boolean isBlocked;

	@Column(name = "timestamp")
	private String timestamp;

	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@JsonIgnore
	@OneToOne(mappedBy = "profile", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private BlobEntity avatar;

	/* user profile links i.e. social media accounts, personal websites, blogs ... etc. */
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Link> links;

	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "profile_feature",
			joinColumns = @JoinColumn(name = "profile_id"),
			inverseJoinColumns = @JoinColumn(name = "feature_id"))
	private Set<Feature> features = new HashSet<>();

	@JsonIgnore
	@OneToOne(mappedBy = "profile", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private YoucanIntegration youcanIntegration;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public boolean isActivated() {
		return isActivated;
	}

	public void setActivated(boolean activated) {
		this.isActivated = activated;
	}

	public boolean isBlocked() {
		return isBlocked;
	}

	public void setBlocked(boolean blocked) {
		this.isBlocked = blocked;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Link> getLinks() {
		return links;
	}

	public void setLinks(Set<Link> links) {
		this.links = links;
	}

	public Set<Feature> getFeatures() {
		return features;
	}

	public void setFeatures(Set<Feature> features) {
		this.features = features;
	}

	public ERole getType() {
		return type;
	}

	public void setType(ERole type) {
		this.type = type;
	}

	public BlobEntity getAvatar() {
		return avatar;
	}

	public void setAvatar(BlobEntity avatar) {
		this.avatar = avatar;
	}


	public YoucanIntegration getYoucanIntegration() {
		return youcanIntegration;
	}

	public void setYoucanIntegration(YoucanIntegration youcanIntegration) {
		this.youcanIntegration = youcanIntegration;
	}

	public UUID getIdentifier() {
		return identifier;
	}

	public void setIdentifier(UUID identifier) {
		this.identifier = identifier;
	}
}
