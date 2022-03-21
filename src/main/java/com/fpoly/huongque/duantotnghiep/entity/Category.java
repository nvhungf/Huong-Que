package com.fpoly.huongque.duantotnghiep.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "category", schema = "dbo", catalog = "db_a7d8f6_huongqueapp")
public class Category implements Serializable {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Integer id_Category;

	private String name_Category;
	
	private String image;

	@JsonIgnore
	@OneToMany(mappedBy = "category")
	List<Product> product;
}
