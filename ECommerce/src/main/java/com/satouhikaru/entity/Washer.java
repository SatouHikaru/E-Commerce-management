package com.satouhikaru.entity;

/**
 * @author Pham Nguyen Ha Quang
 * @since  17/11/2018
 */
public class Washer extends Product {

	private String type;
	private String laundryCage;
	private int spinSpeed;
	private String engine;
	private String size;
	private double weight;
	private String standardTechnology;
	
	public Washer() {
	}
	
	public Washer(String name, String productType, double price, int quantity,
			String image, int brand, String description, String countryOfOrigin,
			String type, String laundryCage, int spinSpeed, String engine,
			String size, double weight, String standardTechnology) {
		super(name, productType, price, quantity, image, brand, description, countryOfOrigin);
		this.type = type;
		this.laundryCage = laundryCage;
		this.spinSpeed = spinSpeed;
		this.engine = engine;
		this.size = size;
		this.weight = weight;
		this.standardTechnology = standardTechnology;
	}

	public Washer(long id, String name, String productType, double price, int quantity,
			String image, int brand, String description, String countryOfOrigin,
			String type, String laundryCage, int spinSpeed, String engine,
			String size, double weight, String standardTechnology) {
		super(id, name, productType, price, quantity, image, brand, description, countryOfOrigin);
		this.type = type;
		this.laundryCage = laundryCage;
		this.spinSpeed = spinSpeed;
		this.engine = engine;
		this.size = size;
		this.weight = weight;
		this.standardTechnology = standardTechnology;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLaundryCage() {
		return laundryCage;
	}

	public void setLaundryCage(String laundryCage) {
		this.laundryCage = laundryCage;
	}

	public int getSpinSpeed() {
		return spinSpeed;
	}

	public void setSpinSpeed(int spinSpeed) {
		this.spinSpeed = spinSpeed;
	}

	public String getEngine() {
		return engine;
	}

	public void setEngine(String engine) {
		this.engine = engine;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public String getStandardTechnology() {
		return standardTechnology;
	}

	public void setStandardTechnology(String standardTechnology) {
		this.standardTechnology = standardTechnology;
	}
}