package com.fpoly.huongque.duantotnghiep.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@SuppressWarnings("serial")
@Data
@Entity
public class Bill implements Serializable {

	@Id
	@Column(name = "id_Bill", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idBill;

	@Basic
	@Column(name = "order_Code", nullable = true, length = 50)
	private String order_Code;

	@Basic
	@Column(name = "phone_Receiver", nullable = false, length = 11)
	private String phoneReceiver;

	@Basic
	@Column(name = "email_Receiver", nullable = false, length = 200)
	private String emailReceiver;

	@Column(name = "name_Receiver")
	private String nameReceiver;

	@Column(name = "address_Receiver")
	private String addressReceiver;

	@Basic
	@Temporal(TemporalType.DATE)
	@Column(name = "date_Create", nullable = false)
	private Date dateCreate;

	@Basic
	@Temporal(TemporalType.DATE)
	@Column(name = "date_Success", nullable = false)
	private Date dateSuccess;

	@Basic
	@Column(name = "description_Bill", nullable = false, length = 200)
	private String descriptionBill;

	@Basic
	@Column(name = "total", nullable = false)
	private Double total;

	@Basic
	@Column(name = "transport_Fee", nullable = true)
	private Double transportFee;

	@Basic
	@Column(name = "cancellationReason")
	private String cancellationReason;

	@Basic
	@Column(name = "id_Voucher", nullable = true)
	private Integer idVoucher;

	@Basic
	@Column(name = "id_Shipping", nullable = true)
	private Integer idShipping;

	@Basic
	@Column(name = "order_Status", nullable = true)
	private Integer orderStatus;

	@Basic
	@Column(name = "id_policy", nullable = true)
	private Integer idPolicy;

	@OneToOne
	@JoinColumn(name = "id_Account")
	Account account;

	@OneToMany(mappedBy = "idBill")
	List<BillDetail> billDetail;
}
