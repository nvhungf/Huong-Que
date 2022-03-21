package com.fpoly.huongque.duantotnghiep.service.impl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpoly.huongque.duantotnghiep.dao.ProfitDao;
import com.fpoly.huongque.duantotnghiep.dao.SalesReportDao;
import com.fpoly.huongque.duantotnghiep.dao.StatisticByProductDao;
import com.fpoly.huongque.duantotnghiep.entity.Profit;
import com.fpoly.huongque.duantotnghiep.entity.SalesReport;
import com.fpoly.huongque.duantotnghiep.entity.StatisticByProduct;
import com.fpoly.huongque.duantotnghiep.service.ReportService;

@Service
public class ReportServiceImpl implements ReportService {
	@Autowired
	StatisticByProductDao statisticByProductDao;
	@Autowired
	SalesReportDao salesReportDao;
	@Autowired
	ProfitDao profitDao;

	@Override
	public List<StatisticByProduct> getStatisticByProduct() {
		// TODO Auto-generated method stub
		return statisticByProductDao.findAll();
	}

	@Override
	public List<SalesReport> getSalesReport() {
		// TODO Auto-generated method stub
		return salesReportDao.findAll();
	}

	@Override
	public Profit getProfitByDate() {
		// TODO Auto-generated method stub
		return profitDao.getProfitByDate();
	}
	
	@Override
	public Profit getProfitByMonth() {
		// TODO Auto-generated method stub
		return profitDao.getProfitByMonth();
	}

	@Override
	public SalesReport getSalesReportByDate() {
		// TODO Auto-generated method stub
		return salesReportDao.getSalesReportByDate();
	}
	
	@Override
	public SalesReport getSalesReportByMonth() {
		// TODO Auto-generated method stub
		return salesReportDao.getSalesReportByMonth();
	}

	@Override
	public List<Profit> getProfitDate() {
		// TODO Auto-generated method stub
		return profitDao.getProfitDate();
	}
}
