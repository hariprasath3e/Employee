package com.employee.learning.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonProperty;

//import org.hibernate.annotations.Cascade;
//import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "Employee")
public class Employee {

	public Employee() {
	};

	public Employee(String firstName, String lastName, String department, Date dob, Date doj) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.department = department;
		this.dob = dob;
		this.doj = doj;
//		this.addressList = addressList;
	}

	/**
	 * {"id":1,"firstName":"Saranya", "lastName":"Sathyamoorthy",
	 * "department":"security","dob":"1990-05-19","doj":"2016-01-01",
	 * "addressList":"{"addressLine":"2412 BARKER CIR","city":"west chester",
	 * "state":"PA", "zipcode":"19380"}"}
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty
	private int id;

	@Column
	@JsonProperty
	private String firstName;

	@Column
	@JsonProperty
	private String lastName;

	@Column(name = "DEPARTMENT")
	@JsonProperty
	private String department;

	@Column(name = "DOB")
	@Temporal(TemporalType.DATE)
	@JsonProperty
	private Date dob;

	@Column(name = "DOJ")
	@Temporal(TemporalType.DATE)
	@JsonProperty
	private Date doj;

//	@OneToMany(mappedBy="employee",cascade = CascadeType.ALL)
//	private Set<Address> addressList = new HashSet<Address>();

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Date getDoj() {
		return doj;
	}

	public void setDoj(Date doj) {
		this.doj = doj;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
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
		Employee other = (Employee) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", department="
				+ department + ", dob=" + dob + ", doj=" + doj + "]";
	}

}
