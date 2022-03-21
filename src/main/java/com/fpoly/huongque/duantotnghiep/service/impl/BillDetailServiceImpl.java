package com.fpoly.huongque.duantotnghiep.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpoly.huongque.duantotnghiep.dao.BillDetailDao;
import com.fpoly.huongque.duantotnghiep.entity.BillDetail;
import com.fpoly.huongque.duantotnghiep.service.BillDetailService;

@Service
public class BillDetailServiceImpl implements BillDetailService {
	@Autowired
	BillDetailDao dao;

	@Override
	public BillDetail create(BillDetail billDetail) {
		return dao.save(billDetail);
	}

	@Override
	public BillDetail findById(Integer id) {
		// TODO Auto-generated method stub
		return dao.findById(id).get();
	}

	@Override
	public List<BillDetail> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public List<BillDetail> findByOrderCode(String orderCode) {
		// TODO Auto-generated method stub
		return dao.findByOrderCode(orderCode);
	}

}
