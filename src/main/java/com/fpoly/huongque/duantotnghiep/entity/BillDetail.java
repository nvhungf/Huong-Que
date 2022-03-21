package com.fpoly.huongque.duantotnghiep.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "bill_detail")
public class BillDetail implements Serializable{

    @Id
    @Column(name = "id_Bill_Detail", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idBillDetail;

//    @Basic
//    @Column(name = "id_Bill", nullable = false)
//    private Integer idBill;

//    @Basic
//    @Column(name = "id_Product", nullable = false)
//    private Integer idProduct;

    @Basic
    @Column(name = "price", nullable = false, precision = 0)
    private Double price;

    @Basic
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @OneToOne
	@JoinColumn(name = "id_Product")
	Product product;

    @Column(name = "id_Bill")
    private Integer idBill;

}
