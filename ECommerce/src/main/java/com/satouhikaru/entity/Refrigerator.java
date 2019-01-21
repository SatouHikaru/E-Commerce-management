package com.satouhikaru.entity;

/**
 * @author Pham Nguyen Ha Quang
 * @since  17/11/2018
 */
public class Refrigerator extends Product {

	private String size;
	private double weight;
	private double maxVolumetric;
	private double freezerVolumetric;
	private double iceCubesVolumetric;
	private String coolingTechnology;
	
	public Refrigerator() {
	}
	
	public Refrigerator(String name, String productType, double price, int quantity,
			String image, int brand, String description, String countryOfOrigin,
			String size, double weight, double maxVolumetric, double freezerVolumetric,
			double iceCubesVolumetric, String coolingTechnology) {
		super(name, productType, price, quantity, image, brand, description, countryOfOrigin);
		this.size = size;
		this.weight = weight;
		this.maxVolumetric = maxVolumetric;
		this.freezerVolumetric = freezerVolumetric;
		this.iceCubesVolumetric = iceCubesVolumetric;
		this.coolingTechnology = coolingTechnology;
	}
	
	public Refrigerator(long id, String name, String productType, double price, int quantity,
			String image, int brand, String description, String countryOfOrigin,
			String size, double weight, double maxVolumetric, double freezerVolumetric,
			double iceCubesVolumetric, String coolingTechnology) {
		super(id, name, productType, price, quantity, image, brand, description, countryOfOrigin);
		this.size = size;
		this.weight = weight;
		this.maxVolumetric = maxVolumetric;
		this.freezerVolumetric = freezerVolumetric;
		this.iceCubesVolumetric = iceCubesVolumetric;
		this.coolingTechnology = coolingTechnology;
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

	public double getMaxVolumetric() {
		return maxVolumetric;
	}

	public void setMaxVolumetric(double maxVolumetric) {
		this.maxVolumetric = maxVolumetric;
	}

	public double getFreezerVolumetric() {
		return freezerVolumetric;
	}

	public void setFreezerVolumetric(double freezerVolumetric) {
		this.freezerVolumetric = freezerVolumetric;
	}

	public double getIceCubesVolumetric() {
		return iceCubesVolumetric;
	}

	public void setIceCubesVolumetric(double iceCubesVolumetric) {
		this.iceCubesVolumetric = iceCubesVolumetric;
	}

	public String getCoolingTechnology() {
		return coolingTechnology;
	}

	public void setCoolingTechnology(String coolingTechnology) {
		this.coolingTechnology = coolingTechnology;
	}
	
}