package com.satouhikaru.entity;

/**
 * @author Pham Nguyen Ha Quang
 * @since  17/11/2018
 */
public class Television extends Product {

	private String screenSize;
	private String screenResolution;
	private String imageQuality;

	public Television() {
	}

	public Television(String name, String productType, double price, int quantity,
			String image, int brand, String description, String countryOfOrigin,
			String screenSize, String screenResolution, String imageQuality) {
		super(name, productType, price, quantity, image, brand, description, countryOfOrigin);
		this.screenSize = screenSize;
		this.screenResolution = screenResolution;
		this.imageQuality = imageQuality;
	}
	
	public Television(long id, String name, String productType, double price, int quantity,
			String image, int brand, String description, String countryOfOrigin,
			String screenSize, String screenResolution, String imageQuality) {
		super(id, name, productType, price, quantity, image, brand, description, countryOfOrigin);
		this.screenSize = screenSize;
		this.screenResolution = screenResolution;
		this.imageQuality = imageQuality;
	}

	public String getScreenSize() {
		return screenSize;
	}

	public void setScreenSize(String screenSize) {
		this.screenSize = screenSize;
	}

	public String getScreenResolution() {
		return screenResolution;
	}

	public void setScreenResolution(String screenResolution) {
		this.screenResolution = screenResolution;
	}

	public String getImageQuality() {
		return imageQuality;
	}

	public void setImageQuality(String imageQuality) {
		this.imageQuality = imageQuality;
	}
	
}