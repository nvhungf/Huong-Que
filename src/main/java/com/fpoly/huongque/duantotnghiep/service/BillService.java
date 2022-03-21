package com.fpoly.huongque.duantotnghiep.service;

import java.sql.Date;
import java.util.List;

import com.fpoly.huongque.duantotnghiep.entity.Bill;
import com.fpoly.huongque.duantotnghiep.entity.StatisticByAccount;

public interface BillService {

	Bill create(Bill order);

	List<Bill> getPurchaseHistory(Integer id_Account);

	List<Bill> findAll();

	Bill findById(Integer id);

	StatisticByAccount getStatisticByAccount(Integer id_Account);

	List<Bill> getBillByDate(Date dateFrom, Date dateTo);

	Bill getBill(String order_Code);
}
