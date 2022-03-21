package com.fpoly.huongque.duantotnghiep.service;

import java.util.List;

import com.fpoly.huongque.duantotnghiep.entity.Voucher;

public interface VoucherService {

	void deleteAll();

	void deleteById(String id);

	Voucher findById(String id);

	List<Voucher> findAll();

	<S extends Voucher> S save(S entity);

	<S extends Voucher> S update(S entity);

	
}
