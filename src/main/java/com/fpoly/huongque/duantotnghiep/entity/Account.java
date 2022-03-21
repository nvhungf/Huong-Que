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
@Table(name = "account", schema = "dbo", catalog = "db_a7d8f6_huongqueapp")
public class Account implements Serializable {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int id_Account;
	private String user_Name;
	private String full_Name;
	private Date date_of_birth;
	private Integer id_Image;
	private String email;
	private String phone_Number;
	private String password;
	private boolean is_Enabled;
	private Integer points;
	private Date create_Date;

	@Column(name = "address")
	private String address;

	@OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
	List<Authority> authority;

}
