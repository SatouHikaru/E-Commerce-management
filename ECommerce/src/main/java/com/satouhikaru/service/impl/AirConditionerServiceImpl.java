package com.satouhikaru.service.impl;

import com.satouhikaru.dao.impl.AirConditionerDAOImpl;
import com.satouhikaru.entity.AirConditioner;
import com.satouhikaru.service.AirConditionerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Pham Nguyen Ha Quang
 * @since  17/11/2018
 */
@Service
public class AirConditionerServiceImpl implements AirConditionerService {

	@Autowired
	private AirConditionerDAOImpl airConditionerDAOImpl;
	
	@Override
	public long countAll() {
		return airConditionerDAOImpl.countAll();
	}
	
	@Override
	public long countByFilter(String keySearch, String brands, Double priceFrom, Double priceTo) {
		return airConditionerDAOImpl.countByFilter(keySearch, brands, priceFrom, priceTo);
	}

	@Override
	public List<AirConditioner> getPerPage(long startRow, long maxRows) {
		return airConditionerDAOImpl.getPerPage(startRow, maxRows);
	}
	
	@Override
	public List<AirConditioner> getByFilter(String keySearch, String brands, Double priceFrom, Double priceTo,
			long startRow, long maxRows) {
		return airConditionerDAOImpl.getByFilter(keySearch, brands, priceFrom, priceTo, startRow, maxRows);
	}

	@Override
	public AirConditioner getById(long id) {
		return airConditionerDAOImpl.getById(id);
	}
	
	@Override
	public AirConditioner getByName(String name) {
		return airConditionerDAOImpl.getByName(name);
	}

	@Override
	public void add(AirConditioner airConditioner) {
		airConditionerDAOImpl.add(airConditioner);
	}

	@Override
	public void update(AirConditioner airConditioner) {
		airConditionerDAOImpl.update(airConditioner);
	}

	@Override
	public void delete(long id) {
		airConditionerDAOImpl.delete(id);
	}
	
}