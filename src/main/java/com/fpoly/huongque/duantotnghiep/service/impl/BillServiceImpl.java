package com.fpoly.huongque.duantotnghiep.service.impl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpoly.huongque.duantotnghiep.dao.BillDao;
import com.fpoly.huongque.duantotnghiep.dao.StatisticByAccountDao;
import com.fpoly.huongque.duantotnghiep.entity.Bill;
import com.fpoly.huongque.duantotnghiep.entity.StatisticByAccount;
import com.fpoly.huongque.duantotnghiep.service.BillService;

@Service
public class BillServiceImpl implements BillService {
	@Autowired
	BillDao dao;

	@Autowired
	StatisticByAccountDao statisticByAccountDao;

	@Override
	public Bill create(Bill bill) {
		return dao.save(bill);
	}

	@Override
	public List<Bill> getPurchaseHistory(Integer id_Account) {
		// TODO Auto-generated method stub
		return dao.getPurchaseHistory(id_Account);
	}

	@Override
	public List<Bill> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public Bill findById(Integer id) {
		// TODO Auto-generated method stub
		return dao.findById(id).get();
	}

	@Override
	public StatisticByAccount getStatisticByAccount(Integer id_Account) {
		// TODO Auto-generated method stub
		return statisticByAccountDao.getStatisticByAccount(id_Account);
	}

	@Override
	public List<Bill> getBillByDate(Date dateFrom, Date dateTo) {
		// TODO Auto-generated method stub
		return dao.getBillByDate(dateFrom, dateTo);
	}

	@Override
	public Bill getBill(String order_Code) {
		// TODO Auto-generated method stub
		return dao.getBill(order_Code);
	}

}
