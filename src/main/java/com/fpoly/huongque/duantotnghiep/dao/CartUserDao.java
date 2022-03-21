package com.fpoly.huongque.duantotnghiep.dao;

import com.fpoly.huongque.duantotnghiep.entity.CartUser;
import com.fpoly.huongque.duantotnghiep.entity.Product;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CartUserDao extends JpaRepository<CartUser, Integer> {
	
	
	
	@Query("SELECT p FROM CartUser p WHERE p.account.user_Name = ?1")
	List<CartUser> findByUserName(String name);
	
	@Query("SELECT p FROM CartUser p WHERE p.name = ?1")
	CartUser findByProductName(String name);
	
	@Transactional
	@Modifying
	@Query("update CartUser u set u.quantity = ?1 where u.idCart = ?2")
	void updateCartUserQuantity(Integer quantity, Integer idCart);
	
	@Transactional
	@Modifying
	@Query("delete from CartUser u where u.idCart = ?1")
	void deleteCartUserByCartId(Integer idCart);
	
	@Transactional
	@Modifying
	@Query("delete from CartUser u where u.idProduct = ?1")
	void deleteCartUserProductByIdProduct(Integer idProduct);
	
	@Transactional
	@Modifying
	@Query("delete CartUser u where u.account.id_Account = ?1")
	void deleteUserCartByAccountId(Integer accountId);
}
