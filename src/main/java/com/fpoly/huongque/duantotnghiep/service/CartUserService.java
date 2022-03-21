package com.fpoly.huongque.duantotnghiep.service;

import java.util.List;

import com.fpoly.huongque.duantotnghiep.entity.CartUser;

public interface CartUserService {

	List<CartUser> findByUserName(String name);

	CartUser create(CartUser cartUser);

	void updateCartUserQuantity(Integer quantity, Integer accountId);

	CartUser findByProductName(String name);

	void deleteCartUserByCartId(Integer idCart);

	void deleteCartUserByAccountId(Integer accountId);

	void deleteCartUserProductByIdProduct(Integer idProduct);

}
