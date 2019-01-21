package com.satouhikaru.service.impl;

import com.satouhikaru.dao.impl.WasherDAOImpl;
import com.satouhikaru.entity.Washer;
import com.satouhikaru.service.WasherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Pham Nguyen Ha Quang
 * @since  17/11/2018
 */
@Service
public class WasherServiceImpl implements WasherService {

	@Autowired
	private WasherDAOImpl washerDAOImpl;
	
	@Override
	public long countAll() {
		return washerDAOImpl.countAll();
	}
	
	@Override
	public long countByFilter(String keySearch, String brands, Double priceFrom, Double priceTo) {
		return washerDAOImpl.countByFilter(keySearch, brands, priceFrom, priceTo);
	}

	@Override
	public List<Washer> getPerPage(long startRow, long maxRows) {
		return washerDAOImpl.getPerPage(startRow, maxRows);
	}
	
	@Override
	public List<Washer> getByFilter(String keySearch, String brands, Double priceFrom, Double priceTo,
			long startRow, long maxRows) {
		return washerDAOImpl.getByFilter(keySearch, brands, priceFrom, priceTo, startRow, maxRows);
	}

	@Override
	public Washer getById(long id) {
		return washerDAOImpl.getById(id);
	}
	
	@Override
	public Washer getByName(String name) {
		return washerDAOImpl.getByName(name);
	}

	@Override
	public void add(Washer washer) {
		washerDAOImpl.add(washer);
	}

	@Override
	public void update(Washer washer) {
		washerDAOImpl.update(washer);
	}

	@Override
	public void delete(long id) {
		washerDAOImpl.delete(id);
	}
	
}