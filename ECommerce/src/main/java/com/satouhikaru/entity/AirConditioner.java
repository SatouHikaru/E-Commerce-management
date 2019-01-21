package com.satouhikaru.entity;

/**
 * @author Pham Nguyen Ha Quang
 * @since  17/11/2018
 */
public class AirConditioner extends Product {

	private double weight;
	private String roomVolume;
	private String coldCapacity;
	private String gas;
	
	public AirConditioner() {
	}

	public AirConditioner(String name, String productType, double price, int quantity,
			String image, int brand, String description, String countryOfOrigin,
			double weight, String roomVolume, String coldCapacity, String gas) {
		super(name, productType, price, quantity, image, brand, description, countryOfOrigin);
		this.roomVolume = roomVolume;
		this.coldCapacity = coldCapacity;
		this.gas = gas;
	}

	public AirConditioner(long id, String name, String productType, double price, int quantity,
			String image, int brand, String description, String countryOfOrigin,
			double weight, String roomVolume, String coldCapacity, String gas) {
		super(id, name, productType, price, quantity, image, brand, description, countryOfOrigin);
		this.roomVolume = roomVolume;
		this.coldCapacity = coldCapacity;
		this.gas = gas;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public String getRoomVolume() {
		return roomVolume;
	}

	public void setRoomVolume(String roomVolume) {
		this.roomVolume = roomVolume;
	}

	public String getColdCapacity() {
		return coldCapacity;
	}

	public void setColdCapacity(String coldCapacity) {
		this.coldCapacity = coldCapacity;
	}

	public String getGas() {
		return gas;
	}

	public void setGas(String gas) {
		this.gas = gas;
	}

}