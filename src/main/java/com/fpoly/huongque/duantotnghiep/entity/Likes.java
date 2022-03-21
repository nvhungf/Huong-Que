package com.fpoly.huongque.duantotnghiep.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

import java.io.Serializable;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "likes", schema = "dbo", catalog = "db_a7d8f6_huongqueapp")
public class Likes implements Serializable {

    @Id
    @Column(name = "id_like", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idLike;

    @Basic
    @Column(name = "is_like", nullable = true)
    private Boolean isLike;
    
    @Basic
    @Column(name = "id_Account", nullable = false)
    private Integer idAccount;
    
    @ManyToOne
	@JoinColumn(name = "id_Product")
	Product product;

   
}
