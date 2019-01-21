package com.satouhikaru.service.impl;

import com.satouhikaru.dao.impl.BrandDAOImpl;
import com.satouhikaru.entity.Brand;
import com.satouhikaru.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Pham Nguyen Ha Quang
 * @since  17/11/2018
 */
@Service
public class BrandServiceImpl implements BrandService {

	@Autowired
	private BrandDAOImpl brandDAOImpl;
	
	@Override
	public List<Brand> getAll() {
		return brandDAOImpl.getAll();
	}
	
	@Override
	public List<Brand> getFilter(String productType) {
		return brandDAOImpl.getFilter(productType);
	}
	
}