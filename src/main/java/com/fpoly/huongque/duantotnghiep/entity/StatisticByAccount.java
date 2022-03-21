package com.fpoly.huongque.duantotnghiep.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "StatisticByAccount", schema = "dbo", catalog = "db_a7d8f6_huongqueapp")
public class StatisticByAccount implements Serializable {
	@Id
	Integer id_Account;
	Integer quantity;
	BigDecimal total;

}
