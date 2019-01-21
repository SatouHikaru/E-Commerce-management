package com.satouhikaru.service.impl;

import com.satouhikaru.dao.impl.RefrigeratorDAOImpl;
import com.satouhikaru.entity.Refrigerator;
import com.satouhikaru.service.RefrigeratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Pham Nguyen Ha Quang
 * @since  17/11/2018
 */
@Service
public class RefrigeratorServiceImpl implements RefrigeratorService {

	@Autowired
	private RefrigeratorDAOImpl refrigeratorDAOImpl;
	
	@Override
	public long countAll() {
		return refrigeratorDAOImpl.countAll();
	}
	
	@Override
	public long countByFilter(String keySearch, String brands, Double priceFrom, Double priceTo) {
		return refrigeratorDAOImpl.countByFilter(keySearch, brands, priceFrom, priceTo);
	}

	@Override
	public List<Refrigerator> getPerPage(long startRow, long maxRows) {
		return refrigeratorDAOImpl.getPerPage(startRow, maxRows);
	}
	
	@Override
	public List<Refrigerator> getByFilter(String keySearch, String brands, Double priceFrom, Double priceTo,
			long startRow, long maxRows) {
		return refrigeratorDAOImpl.getByFilter(keySearch, brands, priceFrom, priceTo, startRow, maxRows);
	}

	@Override
	public Refrigerator getById(long id) {
		return refrigeratorDAOImpl.getById(id);
	}
	
	@Override
	public Refrigerator getByName(String name) {
		return refrigeratorDAOImpl.getByName(name);
	}

	@Override
	public void add(Refrigerator refrigerator) {
		refrigeratorDAOImpl.add(refrigerator);
	}

	@Override
	public void update(Refrigerator refrigerator) {
		refrigeratorDAOImpl.update(refrigerator);
	}

	@Override
	public void delete(long id) {
		refrigeratorDAOImpl.delete(id);
	}
	
}