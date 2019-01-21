package com.satouhikaru.service.impl;

import com.satouhikaru.dao.impl.WirelessRouterDAOImpl;
import com.satouhikaru.entity.WirelessRouter;
import com.satouhikaru.service.WirelessRouterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Pham Nguyen Ha Quang
 * @since  17/11/2018
 */
@Service
public class WirelessRouterServiceImpl implements WirelessRouterService {

	@Autowired
	private WirelessRouterDAOImpl wirelessRouterDAOImpl;
	
	@Override
	public long countAll() {
		return wirelessRouterDAOImpl.countAll();
	}
	
	@Override
	public long countByFilter(String keySearch, String brands, Double priceFrom, Double priceTo) {
		return wirelessRouterDAOImpl.countByFilter(keySearch, brands, priceFrom, priceTo);
	}

	@Override
	public List<WirelessRouter> getPerPage(long startRow, long maxRows) {
		return wirelessRouterDAOImpl.getPerPage(startRow, maxRows);
	}
	
	@Override
	public List<WirelessRouter> getByFilter(String keySearch, String brands, Double priceFrom, Double priceTo,
			long startRow, long maxRows) {
		return wirelessRouterDAOImpl.getByFilter(keySearch, brands, priceFrom, priceTo, startRow, maxRows);
	}

	@Override
	public WirelessRouter getById(long id) {
		return wirelessRouterDAOImpl.getById(id);
	}
	
	@Override
	public WirelessRouter getByName(String name) {
		return wirelessRouterDAOImpl.getByName(name);
	}

	@Override
	public void add(WirelessRouter wirelessRouter) {
		wirelessRouterDAOImpl.add(wirelessRouter);
	}

	@Override
	public void update(WirelessRouter wirelessRouter) {
		wirelessRouterDAOImpl.update(wirelessRouter);
	}

	@Override
	public void delete(long id) {
		wirelessRouterDAOImpl.delete(id);
	}
	
}