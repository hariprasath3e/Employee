package com.employee.learning.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "address")
public class Address {

	public Address() {
	};

	public Address(String addressLine, String city, String state, String zipcode, Date updatedAt) {
		super();
		this.addressLine = addressLine;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
		this.updatedAt = updatedAt;
	}

	/**
	 * {"addressLine":"2412 BARKER CIR","city":"west chester", "state":"PA",
	 * "zipcode":"19380"}
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty
	private int id;

	@Column(name = "ADDRESS_LINE")
	@JsonProperty
	private String addressLine;

	@Column(name = "CITY")
	@JsonProperty
	private String city;

	@Column(name = "STATE")
	@JsonProperty
	private String state;

	@Column(name = "ZIP")
	@JsonProperty
	private String zipcode;

	@Column(name = "updated_at")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonIgnore
	private Date updatedAt;

	@ManyToOne
	@JoinColumn(name = "employee_id", nullable = false)
	@JsonIgnore
	private Employee employee;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddressLine() {
		return addressLine;
	}

	public void setAddressLine(String addressLine) {
		this.addressLine = addressLine;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", addressLine=" + addressLine + ", city=" + city + ", state=" + state
				+ ", zipcode=" + zipcode + ", updatedAt=" + updatedAt + ", employee=" + employee + ", getId()="
				+ getId() + ", getAddressLine()=" + getAddressLine() + ", getCity()=" + getCity() + ", getState()="
				+ getState() + ", getZipcode()=" + getZipcode() + ", getUpdatedAt()=" + getUpdatedAt()
				+ ", getEmployee()=" + getEmployee() + ", hashCode()=" + hashCode() + ", getClass()=" + getClass()
				+ ", toString()=" + super.toString() + "]";
	}

}
