package com.satouhikaru.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.satouhikaru.entity.WirelessRouter;
import com.satouhikaru.service.impl.WirelessRouterServiceImpl;
import com.satouhikaru.utility.CommonUtil;
import com.satouhikaru.utility.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

/**
 * @author Pham Nguyen Ha Quang
 * @since  17/11/2018
 */
@CrossOrigin("http://localhost:4200")
@RestController
public class WirelessRouterController {

	@Autowired
	private WirelessRouterServiceImpl wirelessRouterServiceImpl;

	/**
	 * Count list of wireless routers quantity
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  29/12/2018
	 *
	 * @return long
	 */
	@GetMapping("/wirelessRouters/countAll")
	public long countAll() {
		return wirelessRouterServiceImpl.countAll();
	}

	/**
	 * Count list of wireless routers quantity by filter
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  29/12/2018
	 *
	 * @param keySearch
	 * @param brands
	 * @param priceFrom
	 * @param priceTo
	 * @return long
	 */
	@GetMapping("/wirelessRouters/countFilter")
	public long countByFilter(@RequestParam("keySearch") String keySearch,
			@RequestParam("brands") String brands,
			@RequestParam("priceFrom") double priceFrom,
			@RequestParam("priceTo") double priceTo) {
		String ks = CommonUtil.checkKeySearchIsEmpty(keySearch);
		String br = CommonUtil.splitBrands(brands);
		Double pf = CommonUtil.checkPriceIsNull(priceFrom);
		Double pt = CommonUtil.checkPriceIsNull(priceTo);

		long result = 0;
		if (!(ks == null && br == null && pf == null)) {
			result = wirelessRouterServiceImpl.countByFilter(ks, br, pf, pt);
		}

		return result;
	}

	/**
	 * Get list of wireless routers per page
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param page
	 * @return List
	 */
	@GetMapping("/wirelessRouters/page/{page}")
	public List<WirelessRouter> getPerPage(@PathVariable("page") long page) {
		List<WirelessRouter> wirelessRouterList = wirelessRouterServiceImpl.getPerPage(
				Constant.ROWS_PER_PAGE * page, Constant.ROWS_PER_PAGE);
		for (WirelessRouter wirelessRouter : wirelessRouterList) {
			wirelessRouter.setImage(Constant.WIRELESS_ROUTER_PATH + wirelessRouter.getImage());
		}
		
		return wirelessRouterList;
	}

	/**
	 * Get list of wireless routers by brand
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param keySearch
	 * @param brands
	 * @param priceFrom
	 * @param priceTo
	 * @param page
	 * @return List
	 */
	@GetMapping("/wirelessRouters/filter/{page}")
	public List<WirelessRouter> getByFilter(@RequestParam("keySearch") String keySearch,
			@RequestParam("brands") String brands,
			@RequestParam("priceFrom") double priceFrom,
			@RequestParam("priceTo") double priceTo,
			@PathVariable("page") long page) {
		String ks = CommonUtil.checkKeySearchIsEmpty(keySearch);
		String br = CommonUtil.splitBrands(brands);
		Double pf = CommonUtil.checkPriceIsNull(priceFrom);
		Double pt = CommonUtil.checkPriceIsNull(priceTo);

		List<WirelessRouter> wirelessRouterList = null;
		if (!(ks == null && br == null && pf == null)) {
			wirelessRouterList = wirelessRouterServiceImpl.getByFilter(ks, br, pf, pt,
					Constant.ROWS_PER_PAGE * page, Constant.ROWS_PER_PAGE);
			for (WirelessRouter wirelessRouter : wirelessRouterList) {
				wirelessRouter.setImage(Constant.WIRELESS_ROUTER_PATH + wirelessRouter.getImage());
			}
		}
		
		return wirelessRouterList;
	}

	/**
	 * Get wireless router by name
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param name wireless router name
	 * @return WirelessRouter
	 */
	@GetMapping("/wirelessRouters/{name}")
	public WirelessRouter getByName(@PathVariable("name") String name) {
		WirelessRouter wirelessRouter = wirelessRouterServiceImpl.getByName(name);
		wirelessRouter.setImage(Constant.WIRELESS_ROUTER_PATH + wirelessRouter.getImage());
		return wirelessRouter;
	}

	/**
	 * Add new wireless router
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param wirelessRouterParam new wirelessRouter
	 * @param fileUpload image file
	 */
	@PostMapping("/wirelessRouters")
	public void add(@RequestParam("wirelessRouter") String wirelessRouterParam,
			@RequestParam("fileUpload") MultipartFile fileUpload) {
		try {
			WirelessRouter wirelessRouter = new ObjectMapper().readValue(wirelessRouterParam, WirelessRouter.class);
			wirelessRouter.setImage(CommonUtil.getImageName(wirelessRouter.getImage()));
			wirelessRouterServiceImpl.add(wirelessRouter);
			
			// Save image file
			BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(fileUpload.getBytes()));
			CommonUtil.saveImageFile(Constant.WIRELESS_ROUTER_PATH, wirelessRouter.getImage(), bufferedImage);
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	/**
	 * Update wireless router
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param id wireless router ID
	 * @param wirelessRouterParam wireless router need updating
	 * @param fileUpload
	 */
	@PostMapping("/wirelessRouters/{id}")
	public void update(@PathVariable("id") long id,
			@RequestParam("wirelessRouter") String wirelessRouterParam,
			@RequestParam(value = "fileUpload", required = false) MultipartFile fileUpload) {
		try {
			WirelessRouter wr = wirelessRouterServiceImpl.getById(id);
			if (wr == null) {
				return;
			}
			
			WirelessRouter wirelessRouter = new ObjectMapper().readValue(wirelessRouterParam, WirelessRouter.class);
			
			boolean imageChanged = false;
			if (fileUpload != null) {
				wirelessRouter.setImage(CommonUtil.getImageName(wirelessRouter.getImage()));
				if (!wirelessRouter.getImage().equals(wr.getImage())) {
					imageChanged = true;
				}
			}
			
			wirelessRouterServiceImpl.update(wirelessRouter);
			
			if (!imageChanged) {
				return;
			}
			
			// Delete old image file
			CommonUtil.deleteImageFile(Constant.WIRELESS_ROUTER_PATH, wr.getImage());
			
			// Save new image file
			BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(fileUpload.getBytes()));
			CommonUtil.saveImageFile(Constant.WIRELESS_ROUTER_PATH, wirelessRouter.getImage(), bufferedImage);
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	/**
	 * Delete wireless router
	 *
	 * @author Pham Nguyen Ha Quang
	 * @since  15/12/2018
	 *
	 * @param id wireless router ID
	 */
	@DeleteMapping("/wirelessRouters/{id}")
	public void delete(@PathVariable("id") long id) {
		WirelessRouter wirelessRouter = wirelessRouterServiceImpl.getById(id);
		if (wirelessRouter == null) {
			return;
		}
		
		wirelessRouterServiceImpl.delete(id);
		CommonUtil.deleteImageFile(Constant.WIRELESS_ROUTER_PATH, wirelessRouter.getImage());
	}
	
}