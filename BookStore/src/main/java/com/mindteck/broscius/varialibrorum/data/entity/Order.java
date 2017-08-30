package com.mindteck.broscius.varialibrorum.data.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "purchase")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	private Long orderNumber;

	@OneToMany(cascade = CascadeType.ALL)
	private Set<OrderItem> items;

	@OneToOne
	private User user;

	private LocalDate date;

	private String billingName;

	private String phone;

	private String billingEmail;

	@Embedded
	private Address address;

	{
		items = new HashSet<>();
	}

	public Order() {

	}

	public Order(User user) {
		this.user = user;
	}

	public Order(Set<OrderItem> items, User user, LocalDate date, String billingName, String phone, String billingEmail,
			Address address) {
		this.items = items;
		this.user = user;
		this.date = date;
		this.billingName = billingName;
		this.phone = phone;
		this.billingEmail = billingEmail;
		this.address = address;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Long orderNumber) {
		this.orderNumber = orderNumber;
	}

	public long obfuscateID() {
		return ((id * 127 + 89) >>> 2) / 3;
	}

	public Set<OrderItem> getItems() {
		return items;
	}

	public void setItems(Set<OrderItem> items) {
		this.items = items;
	}

	public void addItem(OrderItem orderItem) {
		items.add(orderItem);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getBillingName() {
		return billingName;
	}

	public void setBillingName(String billingName) {
		this.billingName = billingName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getBillingEmail() {
		return billingEmail;
	}

	public void setBillingEmail(String billingEmail) {
		this.billingEmail = billingEmail;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address billingAddress) {
		this.address = billingAddress;
	}

	public double calculateTotal() {
		double total = items.stream().mapToDouble(OrderItem::calculateTotal).reduce(0.0f, (x, y) -> x + y);

		return total;
	}

	public int countItems() {
		return items.size();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((billingEmail == null) ? 0 : billingEmail.hashCode());
		result = prime * result + ((billingName == null) ? 0 : billingName.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((items == null) ? 0 : items.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		Order other = (Order) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (billingEmail == null) {
			if (other.billingEmail != null)
				return false;
		} else if (!billingEmail.equals(other.billingEmail))
			return false;
		if (billingName == null) {
			if (other.billingName != null)
				return false;
		} else if (!billingName.equals(other.billingName))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (items == null) {
			if (other.items != null)
				return false;
		} else if (!items.equals(other.items))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", orderNumber=" + orderNumber + ", items=" + items + ", user=" + user + ", date="
				+ date + ", billingName=" + billingName + ", phone=" + phone + ", billingEmail=" + billingEmail
				+ ", address=" + address + "]";
	}

}
