package com.bytesedge.transdoc.idm.entity;

import java.time.Instant;


import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import com.bytesedge.transdoc.idm.common.Status;
import com.bytesedge.transdoc.idm.common.UserType;

import jakarta.persistence.Entity;

@Entity
@Table(name = "identity_information")
public class IdentityInformation {

	@Id
	@Column(name = "ID", length = 36, unique = true, nullable = false)
	private String id;

	@CreationTimestamp
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Column(name = "CREATED_TIME", nullable = false)
	protected Instant createdTime;

	@UpdateTimestamp
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Column(name = "UPDATED_TIME", nullable = true)
	protected Instant updatedTime;

	@Enumerated(EnumType.STRING)
	@Column(name = "STATUS", length = 12, nullable = false)
	private Status status = Status.ACTIVE;

	@Column(name = "EMAIL", length = 255, nullable = false)
	private String email;

	@Column(name = "PHONE_NUMBER", length = 12, nullable = false)
	private String phoneNumber;

	@Column(name = "PASSWORD", length = 255, nullable = true)
	private String password;

	@Column(name = "NAME", length = 64, nullable = false)
	private String name;

	@Column(name = "PROFILE_PIC", length = 225, nullable = true)
	private String profilePic;

	@Enumerated(EnumType.STRING)
	@Column(name = "TYPE_OF_USER", length = 255, nullable = false)
	private UserType userType;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public Instant getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Instant createdTime) {
		this.createdTime = createdTime;
	}

	public Instant getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Instant updatedTime) {
		this.updatedTime = updatedTime;
	}

	public String getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

	public IdentityInformation() {
		super();
		// TODO Auto-generated constructor stub
	}
}