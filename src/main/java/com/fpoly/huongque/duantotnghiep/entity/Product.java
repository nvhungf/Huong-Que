package com.fpoly.huongque.duantotnghiep.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "product", schema = "dbo", catalog = "db_a7d8f6_huongqueapp")
public class Product implements Serializable {

	@Id
	@Column(name = "id_Product", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idProduct;

	@Basic
	@Column(name = "name_Product", nullable = false, length = 100)
	private String nameProduct;

	@Basic
	@Column(name = "description_Product", nullable = true, length = 500)
	private String descriptionProduct;

	private Date dateCreate;

	@Basic
	@Column(name = "is_Enabled", nullable = false)
	private Boolean isEnabled;

	@Basic
	@Column(name = "price", nullable = true, precision = 0)
	private Double price;
	@Basic
	@Column(name = "cost", nullable = true, precision = 0)
	private Double cost;

	@Basic
	@Column(name = "quantity", nullable = true)
	private Integer quantity;

	@Basic
	@Column(name = "image", nullable = false, length = 500)
	private String image;

	@ManyToOne
	@JoinColumn(name = "id_Category")
	Category category;

	@ManyToOne
	@JoinColumn(name = "id_Region")
	Regions regions;

	@JsonIgnore
	@OneToMany(mappedBy = "product")
	List<Likes> like;

	@JsonIgnore
	@OneToMany(mappedBy = "product")
	List<Feedback> feedback;

	@OneToMany(mappedBy = "product")
	List<Image> images;
}
