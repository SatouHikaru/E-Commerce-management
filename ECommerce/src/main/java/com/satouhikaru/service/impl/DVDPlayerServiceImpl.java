package com.satouhikaru.service.impl;

import com.satouhikaru.dao.impl.DVDPlayerDAOImpl;
import com.satouhikaru.entity.DVDPlayer;
import com.satouhikaru.service.DVDPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Pham Nguyen Ha Quang
 * @since  17/11/2018
 */
@Service
public class DVDPlayerServiceImpl implements DVDPlayerService {

	@Autowired
	private DVDPlayerDAOImpl dvdPlayerDAOImpl;
	
	@Override
	public long countAll() {
		return dvdPlayerDAOImpl.countAll();
	}
	
	@Override
	public long countByFilter(String keySearch, String brands, Double priceFrom, Double priceTo) {
		return dvdPlayerDAOImpl.countByFilter(keySearch, brands, priceFrom, priceTo);
	}

	@Override
	public List<DVDPlayer> getPerPage(long startRow, long maxRows) {
		return dvdPlayerDAOImpl.getPerPage(startRow, maxRows);
	}
	
	@Override
	public List<DVDPlayer> getByFilter(String keySearch, String brands, Double priceFrom, Double priceTo,
			long startRow, long maxRows) {
		return dvdPlayerDAOImpl.getByFilter(keySearch, brands, priceFrom, priceTo, startRow, maxRows);
	}

	@Override
	public DVDPlayer getById(long id) {
		return dvdPlayerDAOImpl.getById(id);
	}
	
	@Override
	public DVDPlayer getByName(String name) {
		return dvdPlayerDAOImpl.getByName(name);
	}

	@Override
	public void add(DVDPlayer dvdPlayer) {
		dvdPlayerDAOImpl.add(dvdPlayer);
	}

	@Override
	public void update(DVDPlayer dvdPlayer) {
		dvdPlayerDAOImpl.update(dvdPlayer);
	}

	@Override
	public void delete(long id) {
		dvdPlayerDAOImpl.delete(id);
	}
	
}