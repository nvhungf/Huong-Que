package com.fpoly.huongque.duantotnghiep.dao;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fpoly.huongque.duantotnghiep.entity.Profit;
import com.fpoly.huongque.duantotnghiep.entity.SalesReport;

public interface ProfitDao extends JpaRepository<Profit, BigDecimal> {
	@Query(value = " SELECT sum(((product.price - product.cost) * bill_detail2.quantity)) as  profit ,bill_detail2.date_Create as date\r\n"
			+ "					from product  inner join (	select bill_detail.*,date_Create from bill_detail   inner join \r\n"
			+ "					( select * from bill where date_Create = ( SELECT (CONVERT(VARCHAR(10), getdate(), 1))) ) as bill2\r\n"
			+ "						on bill_detail.id_Bill = bill2.id_Bill) AS bill_detail2\r\n"
			+ "					on product.id_Product = bill_detail2.id_Product\r\n"
			+ "					group by bill_detail2.date_Create", nativeQuery = true)
	Profit getProfitByDate();

	@Query(value = "SELECT sum(((product.price - product.cost) * bill_detail2.quantity)) as  profit ,  date = ('2021-12-12')\r\n"
			+ "					from product  inner join (	select bill_detail.* from bill_detail   inner join \r\n"
			+ "					( select * from bill where month(date_Create) = ( SELECT month(CONVERT(VARCHAR(10), getdate(), 1))) ) as bill2\r\n"
			+ "						on bill_detail.id_Bill = bill2.id_Bill) AS bill_detail2\r\n"
			+ "					on product.id_Product = bill_detail2.id_Product", nativeQuery = true)
	Profit getProfitByMonth();

	@Query(value = "SELECT sum(((product.price - product.cost) * bill_detail2.quantity)) as  profit, bill_detail2.date_Create as date\r\n"
			+ "					from product  inner join (select bill_detail.*, date_Create from bill_detail inner join \r\n"
			+ "					bill on bill_detail.id_Bill = bill.id_Bill) AS bill_detail2\r\n"
			+ "					on product.id_Product = bill_detail2.id_Product\r\n"
			+ "					group by bill_detail2.date_Create", nativeQuery = true)
	List<Profit> getProfitDate();

}
