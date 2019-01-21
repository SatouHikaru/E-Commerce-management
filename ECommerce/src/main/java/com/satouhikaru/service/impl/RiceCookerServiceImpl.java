package com.satouhikaru.service.impl;

import com.satouhikaru.dao.impl.RiceCookerDAOImpl;
import com.satouhikaru.entity.RiceCooker;
import com.satouhikaru.service.RiceCookerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Pham Nguyen Ha Quang
 * @since  17/11/2018
 */
@Service
public class RiceCookerServiceImpl implements RiceCookerService {

	@Autowired
	private RiceCookerDAOImpl riceCookerDAOImpl;
	
	@Override
	public long countAll() {
		return riceCookerDAOImpl.countAll();
	}
	
	@Override
	public long countByFilter(String keySearch, String brands, Double priceFrom, Double priceTo) {
		return riceCookerDAOImpl.countByFilter(keySearch, brands, priceFrom, priceTo);
	}

	@Override
	public List<RiceCooker> getPerPage(long startRow, long maxRows) {
		return riceCookerDAOImpl.getPerPage(startRow, maxRows);
	}
	
	@Override
	public List<RiceCooker> getByFilter(String keySearch, String brands, Double priceFrom, Double priceTo,
			long startRow, long maxRows) {
		return riceCookerDAOImpl.getByFilter(keySearch, brands, priceFrom, priceTo, startRow, maxRows);
	}

	@Override
	public RiceCooker getById(long id) {
		return riceCookerDAOImpl.getById(id);
	}
	
	@Override
	public RiceCooker getByName(String name) {
		return riceCookerDAOImpl.getByName(name);
	}

	@Override
	public void add(RiceCooker riceCooker) {
		riceCookerDAOImpl.add(riceCooker);
	}

	@Override
	public void update(RiceCooker riceCooker) {
		riceCookerDAOImpl.update(riceCooker);
	}

	@Override
	public void delete(long id) {
		riceCookerDAOImpl.delete(id);
	}
	
}