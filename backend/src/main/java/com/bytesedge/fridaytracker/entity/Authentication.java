package com.bytesedge.fridaytracker.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.bytesedge.transdoc.idm.common.Status;

@Entity
@Table(name = "authentication")
public class Authentication {

	@Id
	@Column(name = "ID", length = 36, unique = true, nullable = false)
	protected String id;

	@Column(name = "UUID_TOKEN", length = 36, nullable = false)
	protected String uuidToken;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "CREATED_DATE", nullable = false)
	protected Date createdDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "END_DATE", nullable = false)
	protected Date endDate;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "IDENTITY_ID", nullable = true)
	protected IdentityInformation identityInformation;

	@Enumerated(EnumType.STRING)
	@Column(name = "STATUS", length = 12, nullable = false)
	protected Status status = Status.ACTIVE;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ORGANIZATION_ID", nullable = true)
	protected Organization organizationId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUuidToken() {
		return uuidToken;
	}

	public void setUuidToken(String uuidToken) {
		this.uuidToken = uuidToken;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public IdentityInformation getIdentityInformation() {
		return identityInformation;
	}

	public void setIdentityInformation(IdentityInformation identityInformation) {
		this.identityInformation = identityInformation;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Authentication() {
		super();
		// TODO Auto-generated constructor stub
	}

}