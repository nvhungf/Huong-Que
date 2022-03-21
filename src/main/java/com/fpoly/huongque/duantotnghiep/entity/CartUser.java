package com.fpoly.huongque.duantotnghiep.entity;

import javax.persistence.*;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@SuppressWarnings("serial")
@Data
@Entity
public class CartUser implements Serializable{

    @Id
    @Column(name = "id_Cart", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCart;
    
    @Basic
    @Column(name = "id_Product", nullable = false)
    private Integer idProduct;
    
    @Basic
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Basic
    @Column(name = "price", nullable = false)
    private double price;

    @Basic
    @Column(name = "quantity", nullable = true)
    private Integer quantity = 1;
    
    @Basic
    @Column(name = "image", nullable = true)
    private String image;
    

    @ManyToOne
	@JoinColumn(name = "account_Id")
	Account account;
    
}
