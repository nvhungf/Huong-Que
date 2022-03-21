package com.fpoly.huongque.duantotnghiep.dao;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fpoly.huongque.duantotnghiep.entity.Profit;
import com.fpoly.huongque.duantotnghiep.entity.SalesReport;

public interface SalesReportDao extends JpaRepository<SalesReport, Integer> {
	@Query(value = "select date_Create as date ,count(id_Bill) as quantity,sum(total) as total from bill where date_Create = ( SELECT (CONVERT(VARCHAR(10), getdate(), 1)))\r\n"
			+ "group by date_Create", nativeQuery = true)
	SalesReport getSalesReportByDate();
	
	@Query(value = "select * from getSalesReportByMonth", nativeQuery = true)
	SalesReport getSalesReportByMonth();

}
