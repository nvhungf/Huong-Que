package com.fpoly.huongque.duantotnghiep.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "authority", uniqueConstraints = {
		@UniqueConstraint(columnNames = {"id_Account", "id_Role"})
})
public class Authority  implements Serializable{
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "id_Account")
	@ToString.Exclude
	@JsonIgnore
	private Account account;

	@ManyToOne  @JoinColumn(name = "id_Role")
	@ToString.Exclude
	private Role role;

}