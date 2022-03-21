package com.fpoly.huongque.duantotnghiep.service;

import java.util.List;

import com.fpoly.huongque.duantotnghiep.entity.BillDetail;
import com.fpoly.huongque.duantotnghiep.entity.Product;

public interface BillDetailService {

	BillDetail create(BillDetail orderDetail);

	BillDetail findById(Integer id);

	List<BillDetail> findAll();

	List<BillDetail> findByOrderCode(String orderCode);

}
