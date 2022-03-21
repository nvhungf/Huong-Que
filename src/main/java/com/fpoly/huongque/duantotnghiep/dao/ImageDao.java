package com.fpoly.huongque.duantotnghiep.dao;

import com.fpoly.huongque.duantotnghiep.entity.Image;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ImageDao extends JpaRepository<Image, Integer> {
	
	@Query(value = "select * from image where id_Product = ?", nativeQuery = true)
	List<Image> GetImageByProduct(Integer id_product);

}
