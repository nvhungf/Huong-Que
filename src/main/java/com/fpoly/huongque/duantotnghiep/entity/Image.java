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

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "Image", schema = "dbo", catalog = "db_a7d8f6_huongqueapp")
public class Image implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_Image;

	private String name_Image;

	private String path_Image;

	private Date date_Upload;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_Product")
	Product product;
}
