package com.satouhikaru.entity;

/**
 * @author Pham Nguyen Ha Quang
 * @since  17/11/2018
 */
public class DVDPlayer extends Product {

	private String usb;
	private double weight;
	private String size;

	public DVDPlayer() {
	}

	public DVDPlayer(String name, String productType, double price, int quantity,
			String image, int brand, String description, String countryOfOrigin,
			String usb, double weight, String size) {
		super(name, productType, price, quantity, image, brand, description, countryOfOrigin);
		this.usb = usb;
		this.weight = weight;
		this.size = size;
	}
	
	public DVDPlayer(long id, String name, String type, double price, int quantity,
			String image, int brand, String description, String countryOfOrigin,
			String usb, double weight, String size) {
		super(id, name, type, price, quantity, image, brand, description, countryOfOrigin);
		this.usb = usb;
		this.weight = weight;
		this.size = size;
	}

	public String getUsb() {
		return usb;
	}

	public void setUsb(String usb) {
		this.usb = usb;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

}