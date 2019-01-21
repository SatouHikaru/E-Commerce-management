package com.satouhikaru.entity;

/**
 * @author Pham Nguyen Ha Quang
 * @since  17/11/2018
 */
public class WirelessRouter extends Product {

	public WirelessRouter() {
	}

	public WirelessRouter(String name, String productType, double price, int quantity,
			String image, int brand, String description, String countryOfOrigin) {
		super(name, productType, price, quantity, image, brand, description, countryOfOrigin);
	}
	
	public WirelessRouter(long id, String name, String productType, double price, int quantity,
			String image, int brand, String description, String countryOfOrigin) {
		super(id, name, productType, price, quantity, image, brand, description, countryOfOrigin);
	}
	
}