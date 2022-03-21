package com.fpoly.huongque.duantotnghiep.dao;

import java.sql.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fpoly.huongque.duantotnghiep.entity.Category;
import com.fpoly.huongque.duantotnghiep.entity.Feedback;
import com.fpoly.huongque.duantotnghiep.entity.Image;
import com.fpoly.huongque.duantotnghiep.entity.Likes;
import com.fpoly.huongque.duantotnghiep.entity.Regions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



//@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor


public class Cart {
	private Integer idProduct;
	private String nameProduct;

	private String descriptionProduct;

	private Date dateCreate;

	private Boolean isEnabled;

	private Double price;

	private int quantity = 1;

	private String image;

	Category category;

	Regions regions;
	
}

