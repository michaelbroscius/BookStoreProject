package com.mindteck.broscius.varialibrorum.data.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "shoppingcart")
public class ShoppingCart {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@OneToMany
	private Set<CartItem> cart;

	@OneToOne
	private User user;

	{
		cart = new HashSet<>();
	}

	public ShoppingCart() {

	}

	public ShoppingCart(User user) {
		this.user = user;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<CartItem> getCart() {
		return cart;
	}

	public void setCart(Set<CartItem> cart) {
		this.cart = cart;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean addItem(CartItem cartItem) {
		return cart.add(cartItem);
	}

	public boolean removeItem(CartItem cartItem) {
		return cart.remove(cartItem);
	}

	public double calculateTotal() {
		double total = cart.stream().mapToDouble(CartItem::calculateTotal).reduce(0.0f, (x, y) -> x + y);

		return total;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cart == null) ? 0 : cart.hashCode());
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
		ShoppingCart other = (ShoppingCart) obj;
		if (cart == null) {
			if (other.cart != null)
				return false;
		} else if (!cart.equals(other.cart))
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
		return "ShoppingCart [id=" + id + ", cart=" + cart + ", user=" + user + "]";
	}

}
