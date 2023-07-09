package com.eric.furniturehubapi.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Product")
public class Product implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="title")
	private String title;
	
	@Column(name="unit_price")
	private double unitPrice;
	
	@Column(name="description")
	private String description;
	
	@Column(name="category")
	private String category;
	
	@Column(name="image_url")
	private String imageURL;
	
	@Column(name="stock_quantity")
	@NotNull
	private int stockQuantity;
	
	
	public Product() {
		super();
	}

	
	public Product(Integer id, String title, double unitPrice, String description, String category, String imageURL,
			int stockQuantity) {
		super();
		this.id = id;
		this.title = title;
		this.unitPrice = unitPrice;
		this.description = description;
		this.category = category;
		this.imageURL = imageURL;
		this.stockQuantity = stockQuantity;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public double getUnitPrice() {
		return unitPrice;
	}


	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public String getImageURL() {
		return imageURL;
	}


	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}


	public int getStockQuantity() {
		return stockQuantity;
	}


	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}


	@Override
	public String toString() {
		return "Product [id=" + id + ", title=" + title + ", unitPrice=" + unitPrice + ", description=" + description
				+ ", category=" + category + ", imageURL=" + imageURL + ", stockQuatity=" + stockQuantity + "]";
	}
	
	

}
