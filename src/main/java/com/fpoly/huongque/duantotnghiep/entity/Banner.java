package com.fpoly.huongque.duantotnghiep.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Banner {

	@Id
	@Column(name = "id_Banner", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idBanner;

	@Basic
	@Column(name = "name_Banner", nullable = false, length = 100)
	private String nameBanner;

	@Basic
	@Column(name = "link_Banner", nullable = false, length = 500)
	private String linkBanner;

	@Basic
	@Column(name = "description_Banner", nullable = true, length = 500)
	private String descriptionBanner;
}
