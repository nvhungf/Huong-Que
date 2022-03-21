package com.fpoly.huongque.duantotnghiep.service.impl;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpoly.huongque.duantotnghiep.dao.BannerDao;
import com.fpoly.huongque.duantotnghiep.dao.VoucherDao;
import com.fpoly.huongque.duantotnghiep.entity.Banner;
import com.fpoly.huongque.duantotnghiep.entity.Voucher;
import com.fpoly.huongque.duantotnghiep.service.BannerService;
import com.fpoly.huongque.duantotnghiep.service.VoucherService;


@Service
public class BannerServiceImpl implements BannerService {
	@Autowired
	BannerDao bannerDao;


	@Override
	public List<Banner> findAll() {
		return bannerDao.findAll();
	}

	@Override
	public Banner findById(Integer id) {
		return bannerDao.findById(id).get();
	}

	@Override
	public void deleteById(Integer id) {
		bannerDao.deleteById(id);
	}

	@Override
	public void deleteAll() {
		bannerDao.deleteAll();
	}

	@Override
	public <S extends Banner> S save(S entity) {
		return bannerDao.save(entity);
	}
	
	@Override
	public <S extends Banner> S update(S entity) {
		return bannerDao.save(entity);
	}

}
