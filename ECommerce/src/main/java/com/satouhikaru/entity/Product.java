package com.satouhikaru.entity;

/**
 * @author Pham Nguyen Ha Quang
 * @since  17/11/2018
 */
public class Product {

	private long id;
	private String name;
	private String productType;
	private double price;
	private int quantity;
	private String image;
	private Brand brand;
	private int brandID;
	private String description;
	private String countryOfOrigin;
	
	public Product() {
	}
	
	public Product(String name, String productType, double price, int quantity,
			String image, int brandID, String description, String countryOfOrigin) {
		this.name = name;
		this.productType = productType;
		this.price = price;
		this.quantity = quantity;
		this.image = image;
		this.brandID = brandID;
		this.description = description;
		this.countryOfOrigin = countryOfOrigin;
	}
	
	public Product(long id, String name, String productType, double price, int quantity,
			String image, int brandID, String description, String countryOfOrigin) {
		this.id = id;
		this.name = name;
		this.productType = productType;
		this.price = price;
		this.quantity = quantity;
		this.image = image;
		this.brandID = brandID;
		this.description = description;
		this.countryOfOrigin = countryOfOrigin;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public int getBrandID() {
		return brandID;
	}

	public void setBrandID(int brandID) {
		this.brandID = brandID;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCountryOfOrigin() {
		return countryOfOrigin;
	}

	public void setCountryOfOrigin(String countryOfOrigin) {
		this.countryOfOrigin = countryOfOrigin;
	}
	
}