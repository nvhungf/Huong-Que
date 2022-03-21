package com.fpoly.huongque.duantotnghiep.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "role", schema = "dbo", catalog = "db_a7d8f6_huongqueapp")
@NoArgsConstructor
@AllArgsConstructor
public class Role implements Serializable {
	@Id
	private String id_Role;
	private String name_Role;
	@JsonIgnore
	@OneToMany(mappedBy = "role")
	List<Authority> authorities;

	public Role(String id_Role) {
		this.id_Role = id_Role;
	}
}
