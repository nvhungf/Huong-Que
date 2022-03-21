package com.fpoly.huongque.duantotnghiep.service;

import java.util.List;

import com.fpoly.huongque.duantotnghiep.entity.Banner;

public interface BannerService {

	List<Banner> findAll();

	Banner findById(Integer id);

	<S extends Banner> S update(S entity);

	<S extends Banner> S save(S entity);

	void deleteAll();

	void deleteById(Integer id);

	

	
}
