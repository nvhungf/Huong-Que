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
import java.sql.Date;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "feedback", schema = "dbo", catalog = "db_a7d8f6_huongqueapp")
public class Feedback implements Serializable {

	@Id
	@Column(name = "id_Feedback", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idFeedback;

	@Column(name = "title")
	private String title;

	@Basic
	@Column(name = "Text", nullable = false, length = 200)
	private String text;

	@Basic
	@Column(name = "date_Post", nullable = false)
	private Date datePost;

	@ManyToOne
	@JoinColumn(name = "id_Account")
	private Account account;
	
	@Basic
	@Column(name = "star", nullable = false)
	private Integer star;
	
	@ManyToOne
	@JoinColumn(name = "id_Product")
	Product product;

}
