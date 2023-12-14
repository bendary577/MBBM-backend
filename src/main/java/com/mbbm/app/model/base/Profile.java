package com.mbbm.app.model.base;

import javax.persistence.*;

/**
 * @author mohamed.bendary
 */
@Entity
@Table(name = "profile")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "about")
    private String about;

	@Column(name = "avatar_link")
	private String avatar_url;

    @Column(name = "activated")
    private boolean activated;

    @Column(name = "blocked")
    private boolean blocked;

    @Column(name = "deleted")
    private boolean deleted;

	@OneToOne(mappedBy = "profile")
	private User user;

	public Profile() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getAvatar_url() {
		return avatar_url;
	}

	public void setAvatar_url(String avatar_url) {
		this.avatar_url = avatar_url;
	}

	public boolean isActivated() {
		return activated;
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
	}

	public boolean isBlocked() {
		return blocked;
	}

	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

}
