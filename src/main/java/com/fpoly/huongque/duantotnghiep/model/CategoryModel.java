package com.fpoly.huongque.duantotnghiep.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryModel {
	@Id
	private Integer id_Category;

	private String name_Category;
	
	private String image;
	private Long sl;
	//ne
}
