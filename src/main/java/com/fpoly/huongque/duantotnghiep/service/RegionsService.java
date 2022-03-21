package com.fpoly.huongque.duantotnghiep.service;

import java.util.List;

import com.fpoly.huongque.duantotnghiep.entity.Regions;

public interface RegionsService {

	List<Regions> findAll();

	Regions findById(Integer id);

	Regions create(Regions regions);

	void deleteById(Integer id);

}
