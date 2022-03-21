package com.fpoly.huongque.duantotnghiep.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "regions", schema = "dbo", catalog = "db_a7d8f6_huongqueapp")
public class Regions implements Serializable {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Integer id_Region;

	private String name_Region;

	@JsonIgnore
	@OneToMany(mappedBy = "regions")
	List<Product> product;
}
