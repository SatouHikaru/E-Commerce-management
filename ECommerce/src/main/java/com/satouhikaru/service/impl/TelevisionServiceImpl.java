package com.satouhikaru.service.impl;

import com.satouhikaru.dao.impl.TelevisionDAOImpl;
import com.satouhikaru.entity.Television;
import com.satouhikaru.service.TelevisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Pham Nguyen Ha Quang
 * @since  17/11/2018
 */
@Service
public class TelevisionServiceImpl implements TelevisionService {

	@Autowired
	private TelevisionDAOImpl televisionDAOImpl;
	
	@Override
	public long countAll() {
		return televisionDAOImpl.countAll();
	}
	
	@Override
	public long countByFilter(String keySearch, String brands, Double priceFrom, Double priceTo) {
		return televisionDAOImpl.countByFilter(keySearch, brands, priceFrom, priceTo);
	}

	@Override
	public List<Television> getPerPage(long startRow, long maxRows) {
		return televisionDAOImpl.getPerPage(startRow, maxRows);
	}
	
	@Override
	public List<Television> getByFilter(String keySearch, String brands, Double priceFrom, Double priceTo,
			long startRow, long maxRows) {
		return televisionDAOImpl.getByFilter(keySearch, brands, priceFrom, priceTo, startRow, maxRows);
	}

	@Override
	public Television getById(long id) {
		return televisionDAOImpl.getById(id);
	}
	
	@Override
	public Television getByName(String name) {
		return televisionDAOImpl.getByName(name);
	}

	@Override
	public void add(Television television) {
		televisionDAOImpl.add(television);
	}

	@Override
	public void update(Television television) {
		televisionDAOImpl.update(television);
	}

	@Override
	public void delete(long id) {
		televisionDAOImpl.delete(id);
	}
	
}