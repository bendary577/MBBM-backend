package com.mbbm.app.model.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mbbm.app.enums.EGender;
import com.mbbm.app.multitenant.TenantSupport;
import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * @author mohamed.bendary
 * the base user model definition in the system
 */
//TODO:REVISE MULTITENANT SOLUTION
//"@FilterDef", "@Filter" allows injecting a tenant discriminator clause to every SQL query generated for this entity.
//@FilterDef(name = "tenantFilter", parameters = {@ParamDef(name = "tenantId", type = "string")})
//@Filter(name = "tenantFilter", condition = "tenant_id = :tenantId")
@Entity
@Table(name = "user",
		uniqueConstraints = {
				@UniqueConstraint(columnNames = "username"),
				@UniqueConstraint(columnNames = "email")
		})
public class User implements Serializable, TenantSupport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstName")
    @NotBlank(message = "First Name is mandatory")
	@Size(max = 20)
    private String firstName;

	@Column(name = "lastName")
	@Size(max = 20)
	private String lastName;

	/* used for login, just like user email */
    @Column(name = "username")
    @NotBlank(message = "Username is mandatory")
    private String username;

	/* official email for user login - different from contacts personal emails */
    @Column(name = "email")
    @NotBlank(message = "email is mandatory")
	@Size(max = 50)
    @Email
    private String email;

    @Column(name = "password")
    @NotBlank(message = "password is mandatory")
	@Size(max = 120)
    private String password;

	@Column(name = "birthdate")
	private String birthdate;

	@Column(name = "nationality")
	private String nationality;

	@Column(name = "gender")
	private EGender gender;

	@Column(name = "tenant_id")
	private String tenantId;

	@Column(name = "isCompany")
	private boolean isCompany;

	@Column(name = "isDeleted")
	private boolean isDeleted;

	@Column(name = "lastLoginDate")
	private String lastLoginDate;

	@Column(name = "lastFailedLoginDate")
	private String lastFailedLoginDate;

	@Column(name = "passwordUpdateDate")
	private String passwordUpdateDate;

	@Column(name = "updateTimestamp")
	private String timestamp;

	@Column(name = "creationDate")
	private String creationDate;

	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "user_role",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	@JsonIgnore
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Profile profile;

	public <E> User(String username, String password, ArrayList<E> roles) {}

	public User(){}

	@Override
    public void setTenantId(String tenantId) {this.tenantId = tenantId; }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean deleted) {
		this.isDeleted = deleted;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public String getTenantId() {
		return tenantId;
	}

	public String getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(String lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public String getLastFailedLoginDate() {
		return lastFailedLoginDate;
	}

	public void setLastFailedLoginDate(String lastFailedLoginDate) {
		this.lastFailedLoginDate = lastFailedLoginDate;
	}

	public String getPasswordUpdateDate() {
		return passwordUpdateDate;
	}

	public void setPasswordUpdateDate(String passwordUpdateDate) {
		this.passwordUpdateDate = passwordUpdateDate;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public EGender getGender() {
		return gender;
	}

	public void setGender(EGender gender) {
		this.gender = gender;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public boolean isCompany() {
		return isCompany;
	}

	public void setCompany(boolean company) {
		isCompany = company;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
}
