package com.fpoly.huongque.duantotnghiep.service;

import java.util.List;

import com.fpoly.huongque.duantotnghiep.entity.Product;
import com.fpoly.huongque.duantotnghiep.entity.StatisticByProduct;

public interface ProductService {

	List<Product> findAll();

	Product findById(Integer id);

	Product create(Product product);

	void delete(Integer id);

	void update(Product product);

	List<Product> findTop10();

	List<Product> search(String keyword);

	List<Product> findByCategory(String cid);
	
	List<Product> findByRegion(String rid);

	List<Product> findByLikes(String id);

	List<Product> findTop5Trending();
	
	List<Product> getAllProductEnabled();

	

}
