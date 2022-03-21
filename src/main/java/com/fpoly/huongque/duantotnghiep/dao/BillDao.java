package com.fpoly.huongque.duantotnghiep.dao;

import com.fpoly.huongque.duantotnghiep.entity.Bill;
import com.fpoly.huongque.duantotnghiep.entity.StatisticByAccount;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BillDao extends JpaRepository<Bill, Integer> {
	@Query(value = "select * from bill where id_Account = ?", nativeQuery = true)
	List<Bill> getPurchaseHistory(Integer id_Account);
	
	@Query(value = "select * from bill where date_Create between ?1 and ?2", nativeQuery = true)
	List<Bill> getBillByDate(Date dateFrom, Date dateTo);

	@Query(nativeQuery = true, value = "SELECT * FROM bill WHERE order_Code = ?1")
	Optional<Bill> findByOrderCode(String orderCode);
	
	@Query(value = "select * from bill where order_Code  = ?1", nativeQuery = true)
	Bill getBill(String order_Code);
	
}
