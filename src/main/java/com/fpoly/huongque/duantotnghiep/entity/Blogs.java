package com.fpoly.huongque.duantotnghiep.entity;

import javax.persistence.*;

import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Blogs {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long blogid;
	private String name;
	private String title;
	private String shortTitle;
	private String img;
	private String blogcontent;

	@Temporal(TemporalType.DATE)
	@Column(name = "created_Date")
	private Date createdDate;
}
