package com.qa.mySpringBootDatabase.model;




import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
 * @Entity annotation. It is used to
mark the class as a persistent Java class.
 *@Table annotation is used to provide the details of the table that this entity will be
mapped to.
 *
 *@Id annotation is used to define the primary key.
 *
 *@GeneratedValue annotation is used to define the primary key generation
strategy. In the above case, we have declared the primary key to be an Auto
Increment field.
 *
 *@NotBlank annotation is used to validate that the annotated field is not null or
empty.
 *
 *@Column annotation is used to define the properties of the column that will be
mapped to the annotated field. You can define several properties like name,
length, nullable, updateable etc.
 *
 *
 */

@Entity
@Table
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"creationDate","lastModified"}, allowGetters = true)

public class MySpringBootDataModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String name;

	@NotBlank
	private String address;
	
	private Integer age;
	
	@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date creationDate;
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date lastModified;
	

	public MySpringBootDataModel(String name, String address, int age) {
		this.name = name;
		this.address = address;
		this.age = age;
	}
	
	public MySpringBootDataModel() {

	}
	
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

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
		
	}

	public void setAddress(String address) {
		this.address = address;
	}
	


	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}
	

	
}
