package pt.jumia.catalog.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "Customer.getAll", query = "SELECT c from Customer c")
public class Customer {

	@Id
	private Integer id;

	@Column(name = "name", length = 50)
	private String fullName;

	@Column(name = "phone", length = 50)
	private String fullPhoneNumber;

	public Customer() {
	}

	public Customer(Integer id, String fullName, String phoneNumber) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.fullPhoneNumber = phoneNumber;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getFullPhoneNumber() {
		return fullPhoneNumber;
	}

	public void setFullPhoneNumber(String fullPhoneNumber) {
		this.fullPhoneNumber = fullPhoneNumber;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Customer)) {
			return false;
		}
		Customer customer = (Customer) obj;
		return Objects.equals(id, customer.id) && Objects.equals(fullName, customer.getFullName())
				&& Objects.equals(fullPhoneNumber, customer.getFullPhoneNumber());
	}

	@Override
	public String toString() {
		return id + " | " + fullName + " | " + fullPhoneNumber;
	}
}
