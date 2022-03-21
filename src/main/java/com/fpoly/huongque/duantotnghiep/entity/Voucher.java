package com.fpoly.huongque.duantotnghiep.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Voucher {

	@Id
	@Column(name = "id_Voucher", nullable = false)
	private String idVoucher;

	@Basic
	@Column(name = "name_Voucher", nullable = false, length = 100)
	private String nameVoucher;

	@Basic
	@Column(name = "percent_Voucher", nullable = false, precision = 0)
	private Double percentVoucher;

	private Date dateStart;

	private Date dateEnd;

	@Basic
	@Column(name = "is_Enabled", nullable = false)
	private Boolean isEnabled = true;
}
