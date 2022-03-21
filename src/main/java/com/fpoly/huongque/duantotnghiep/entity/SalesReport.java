package com.fpoly.huongque.duantotnghiep.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "sales_report", schema = "dbo", catalog = "db_a7d8f6_huongqueapp")
public class SalesReport implements Serializable {

	@Id
	Integer quantity;
	BigDecimal total;
	Date date;
}
