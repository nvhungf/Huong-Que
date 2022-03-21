package com.fpoly.huongque.duantotnghiep.dao;

import com.fpoly.huongque.duantotnghiep.entity.Product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductDao extends JpaRepository<Product, Integer> {
	@Query(value = "select  product.*  from  product inner join Top10Product on product.id_Product = Top10Product.id_Product where product.is_enabled = 1", nativeQuery = true)
	List<Product> findTop10();

	@Query(value = "select * from product a where a.id_product in \r\n"
			+ " (select top 5  id_product from likes b  group by id_product order by count(id_product) desc)", nativeQuery = true)
	List<Product> findTop5Trending();

	@Query("SELECT p FROM Product p WHERE CONCAT(p.nameProduct, ' ', p.descriptionProduct) LIKE %?1%")
	public List<Product> search(String keyword);
 
	@Query(value = "SELECT * FROM PRODUCT WHERE ID_CATEGORY = ?", nativeQuery = true)
	List<Product> findByCategory(String cid);

	@Query(value = "SELECT * FROM PRODUCT WHERE ID_REGION = ?", nativeQuery = true)
	List<Product> findByRegion(String rid);

	@Query(value = "select a.*   from product a  inner join  likes b on a.id_Product = b.id_Product  where b.id_Account = ?1", nativeQuery = true)
	List<Product> findByLikes(String id);

	@Query(value = "select * from product where is_Enabled = 1", nativeQuery = true)
	List<Product> getAllProductEnabled();
}
