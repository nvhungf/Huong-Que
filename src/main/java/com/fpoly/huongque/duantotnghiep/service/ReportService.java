package com.fpoly.huongque.duantotnghiep.service;

import java.sql.Date;
import java.util.List;

import com.fpoly.huongque.duantotnghiep.entity.Profit;
import com.fpoly.huongque.duantotnghiep.entity.SalesReport;
import com.fpoly.huongque.duantotnghiep.entity.StatisticByProduct;

public interface ReportService {
	List<StatisticByProduct> getStatisticByProduct();

	List<SalesReport> getSalesReport();

	Profit getProfitByDate();

	Profit getProfitByMonth();

	SalesReport getSalesReportByDate();

	SalesReport getSalesReportByMonth();

	List<Profit> getProfitDate();
}
