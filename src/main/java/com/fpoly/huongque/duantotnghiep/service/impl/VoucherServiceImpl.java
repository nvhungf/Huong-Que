package com.fpoly.huongque.duantotnghiep.service.impl;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpoly.huongque.duantotnghiep.dao.VoucherDao;
import com.fpoly.huongque.duantotnghiep.entity.Voucher;
import com.fpoly.huongque.duantotnghiep.service.VoucherService;


@Service
public class VoucherServiceImpl implements VoucherService {
	@Autowired
	VoucherDao voucherDao;


	@Override
	public List<Voucher> findAll() {
		return voucherDao.findAll();
	}

	@Override
	public Voucher findById(String id) {
		return voucherDao.findById(id).get();
	}

	@Override
	public void deleteById(String id) {
		voucherDao.deleteById(id);
	}

	@Override
	public void deleteAll() {
		voucherDao.deleteAll();
	}

	@Override
	public <S extends Voucher> S save(S entity) {
		return voucherDao.save(entity);
	}
	
	@Override
	public <S extends Voucher> S update(S entity) {
		return voucherDao.save(entity);
	}

}
