package com.mbbm.app.model.base;

import com.mbbm.app.enums.EBlobType;

import javax.persistence.*;


/**
 * @author mohamed.bendary
 * */
@Entity
@Table(name = "blob_entity")
public class BlobEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column(name = "name")
	private String name;

    @Column(name = "url")
    private String url;

	@Column(name = "type")
	private EBlobType type;

	@Column(name = "size")
	private String size;

	@Column(name = "timestamp")
	private String timestamp;

	@OneToOne
	@JoinColumn(name = "profile_id")
	private Profile profile;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public EBlobType getType() {
		return type;
	}

	public void setType(EBlobType type) {
		this.type = type;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}
}
