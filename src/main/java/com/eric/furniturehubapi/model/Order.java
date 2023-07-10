package com.eric.furniturehubapi.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Orders")
public class Order implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="quantity")
	private Integer quantity;
	
	@Column(name="hasPurchased")
	private boolean hasPurchased;
	
	@Column(name="purchased_time")
	private LocalDateTime purchasedTime;

	
	@ManyToOne(fetch=FetchType.EAGER, optional = false)
	@JoinColumn(name="user_id" , referencedColumnName = "id")
	private User user;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="product_id", referencedColumnName = "id")
	private Product product;

	public Order(Integer id, Integer quantity, boolean hasPurchased, LocalDateTime purchasedTime, User user,
			Product product, Integer productId) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.hasPurchased = hasPurchased;
		this.purchasedTime = purchasedTime;
		this.user = user;
		this.product = product;
	}

	public Order() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public boolean isHasPurchased() {
		return hasPurchased;
	}

	public void setHasPurchased(boolean hasPurchased) {
		this.hasPurchased = hasPurchased;
	}

	public LocalDateTime getPurchasedTime() {
		return purchasedTime;
	}

	public void setPurchasedTime(LocalDateTime purchasedTime) {
		this.purchasedTime = purchasedTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", quantity=" + quantity + ", hasPurchased=" + hasPurchased + ", purchasedTime="
				+ purchasedTime + ", user=" + user + ", product=" + product + "]";
	}

	
	
	

}
