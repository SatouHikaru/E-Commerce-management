package com.satouhikaru.utility;

import com.satouhikaru.entity.Product;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * @author Pham Nguyen Ha Quang
 * @since  15/12/2018
 */
public class CommonUtil {

	/**
	 * MD5 password hash generator
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param password
	 * @return String
	 */
	public static String md5(String password){
		String result = "";
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("MD5");
			digest.update(password.getBytes());
			BigInteger bigInteger = new BigInteger(1, digest.digest());
			result = bigInteger.toString(16);
		} catch (NoSuchAlgorithmException e) {
			System.out.println(e);
		}

		return result;
	}

	/**
	 * Split brands string to SQL query in WHERE condition
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param brands brands string
	 * @return String
	 */
	public static String splitBrands(String brands) {
		String br = null;
		if (brands.contains(",")) {
			brands = "brand.name = '" + brands + "'";
			br = brands.replace(",", "' and brand.name = '");
			br = "(" + br + ") ";
		} else if (!brands.equals("")) {
			br = "brand.name = '" + brands + "' ";
		}
		
		return br;
	}

	/**
	 * Check key search is empty
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  02/01/2019
	 *
	 * @param keySearch
	 * @return String
	 */
	public static String checkKeySearchIsEmpty(String keySearch) {
		if (keySearch.equals("")) {
			return null;
		}

		return keySearch;
	}
	
	/**
	 * Check price is null
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param price
	 * @return Double
	 */
	public static Double checkPriceIsNull(double price) {
		if (price != 0) {
			return price;
		}
		
		return null;
	}
	
	/**
	 * Split image path to get image name
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param image image path
	 * @return String
	 */
	public static String getImageName(String image) {
		String[] img = image.split("\\\\");
		return img[img.length - 1];
	}
	
	/**
	 * Save image file to folder
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param type item name folder
	 * @param imageFile image file
	 * @param bufferedImage
	 * @throws IOException
	 */
	public static void saveImageFile(String type, String imageFile, BufferedImage bufferedImage)
			throws IOException {
		File file = new File(Constant.ROOT_PATH + type + imageFile);
		String formatFile = imageFile.substring(imageFile.length() - 3, imageFile.length());
		ImageIO.write(bufferedImage, formatFile, file);
		System.out.println("Saved image file successfully!");
	}
	
	/**
	 * Delete image file
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param type item name folder
	 * @param imageFile image file
	 */
	public static void deleteImageFile(String type, String imageFile) {
		File file = new File(Constant.ROOT_PATH + type + imageFile);
		if (file.exists()) {
			file.delete();
			System.out.println("Deleted image file successfully!");
		}
	}

	/**
	 * Get images path for list of products
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  02/01/2019
	 *
	 * @param productList
	 * @return List<Product>
	 */
	public static List<Product> getImagesPath(List<Product> productList) {
		for (Product product : productList) {
			if (product.getProductType().equals("Ti vi")) {
				product.setImage(Constant.TELEVISION_PATH + product.getImage());
			} else if (product.getProductType().equals("Đầu đĩa DVD")) {
				product.setImage(Constant.DVD_PLAYER_PATH + product.getImage());
			} else if (product.getProductType().equals("Tủ lạnh")) {
				product.setImage(Constant.REFRIGERATOR_PATH + product.getImage());
			} else if (product.getProductType().equals("Máy giặt")) {
				product.setImage(Constant.WASHER_PATH + product.getImage());
			} else if (product.getProductType().equals("Điều hoà")) {
				product.setImage(Constant.AIR_CONDITIONER_PATH + product.getImage());
			} else if (product.getProductType().equals("Nồi cơm điện")) {
				product.setImage(Constant.RICE_COOKER_PATH + product.getImage());
			} else if (product.getProductType().equals("Bộ định tuyến")) {
				product.setImage(Constant.WIRELESS_ROUTER_PATH + product.getImage());
			}
		}

		return productList;
	}
	
}