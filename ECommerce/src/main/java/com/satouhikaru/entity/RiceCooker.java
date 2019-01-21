package com.satouhikaru.entity;

/**
 * @author Pham Nguyen Ha Quang
 * @since  17/11/2018
 */
public class RiceCooker extends Product {

	private double volumetric;
	private String timer;
	private String standardTechnology;
	
	public RiceCooker() {
	}
	
	public RiceCooker(String name, String productType, double price, int quantity,
			String image, int brand, String description, String countryOfOrigin,
			double volumetric, String timer, String standardTechnology) {
		super(name, productType, price, quantity, image, brand, description, countryOfOrigin);
		this.volumetric = volumetric;
		this.timer = timer;
		this.standardTechnology = standardTechnology;
	}
	
	public RiceCooker(long id, String name, String productType, double price, int quantity,
			String image, int brand, String description, String countryOfOrigin,
			double volumetric, String timer, String standardTechnology) {
		super(id, name, productType, price, quantity, image, brand, description, countryOfOrigin);
		this.volumetric = volumetric;
		this.timer = timer;
		this.standardTechnology = standardTechnology;
	}

	public double getVolumetric() {
		return volumetric;
	}

	public void setVolumetric(double volumetric) {
		this.volumetric = volumetric;
	}

	public String getTimer() {
		return timer;
	}

	public void setTimer(String timer) {
		this.timer = timer;
	}

	public String getStandardTechnology() {
		return standardTechnology;
	}

	public void setStandardTechnology(String standardTechnology) {
		this.standardTechnology = standardTechnology;
	}
	
}