package com.mbbm.app.model.base;

import javax.persistence.*;

/**
 * @author mohamed.bendary
 * */
@Entity
@Table(name = "feature")
public class Feature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column(name = "name")
	private String name;

    @Column(name = "code")
    private String code;

    @Column(name = "description")
    private String description;

	@Column(name = "isDisabled")
	private boolean isDisabled;

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isDisabled() {
		return isDisabled;
	}

	public void setDisabled(boolean disabled) {
		this.isDisabled = disabled;
	}
}
