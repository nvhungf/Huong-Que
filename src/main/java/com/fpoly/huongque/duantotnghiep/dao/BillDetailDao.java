package com.fpoly.huongque.duantotnghiep.dao;

import com.fpoly.huongque.duantotnghiep.entity.BillDetail;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BillDetailDao extends JpaRepository<BillDetail, Integer> {

	@Query(value = "select bill_detail.id_Bill_Detail,bill_detail.id_Bill,bill_detail.id_Product,bill.total as price,bill_detail.quantity from bill_detail inner join bill\r\n"
			+ "on bill_detail.id_Bill  = bill.id_Bill\r\n" + "where bill.order_Code = ?", nativeQuery = true)
	List<BillDetail> findByOrderCode(String orderCode);

}
