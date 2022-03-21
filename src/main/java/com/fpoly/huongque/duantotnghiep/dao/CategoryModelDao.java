package com.fpoly.huongque.duantotnghiep.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fpoly.huongque.duantotnghiep.model.CategoryModel;

public interface CategoryModelDao extends JpaRepository<CategoryModel, Integer>{
	@Query(value = "select * from category a outer apply(	select COUNT(id_Product) as sl from product p where  a.id_Category = p.id_Category) as sl"
			,nativeQuery = true)
	List<CategoryModel> findAllandSl();//ne
}
