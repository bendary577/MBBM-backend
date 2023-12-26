package com.mbbm.app.model.base;

import com.mbbm.app.enums.ERole;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


/**
 * @author mohamed.bendary
 * */
@Entity
@Table(name = "profile")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column(name = "title")
	private String title;

    @Column(name = "about")
    private String about;

	@Column(name = "type")
	private ERole type;

	@Column(name = "isActivated")
	private boolean isActivated;

	@Column(name = "isBlocked")
	private boolean isBlocked;

	@Column(name = "timestamp")
	private String timestamp;

	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;

	@OneToOne(mappedBy = "profile", cascade = CascadeType.ALL)
	private BlobEntity avatar;

	/* user profile links i.e. social media accounts, personal websites, blogs ... etc. */
	@OneToMany(cascade = CascadeType.ALL)
	private Set<Link> links;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "profile_feature",
			joinColumns = @JoinColumn(name = "profile_id"),
			inverseJoinColumns = @JoinColumn(name = "feature_id"))
	private Set<Feature> features = new HashSet<>();

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

}
