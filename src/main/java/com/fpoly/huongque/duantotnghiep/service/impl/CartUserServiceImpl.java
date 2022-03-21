package com.fpoly.huongque.duantotnghiep.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpoly.huongque.duantotnghiep.dao.CartUserDao;

import com.fpoly.huongque.duantotnghiep.entity.CartUser;

import com.fpoly.huongque.duantotnghiep.service.CartUserService;

@Service
public class CartUserServiceImpl implements CartUserService {
	@Autowired
	CartUserDao dao;

	@Override
	public List<CartUser> findByUserName(String name) {
		return dao.findByUserName(name);
	}

	@Override
	public CartUser findByProductName(String name) {
		return dao.findByProductName(name);
	}

	@Override
	public CartUser create(CartUser cartUser) {
		return dao.save(cartUser);
	}

	@Override
	public void updateCartUserQuantity(Integer quantity, Integer idCart) {
		dao.updateCartUserQuantity(quantity, idCart);
	}

	@Override
	public void deleteCartUserByCartId(Integer idCart) {
		dao.deleteCartUserByCartId(idCart);
	}

	@Override
	public void deleteCartUserProductByIdProduct(Integer idProduct) {
		dao.deleteCartUserProductByIdProduct(idProduct);
	}

	@Override
	public void deleteCartUserByAccountId(Integer accountId) {
		dao.deleteUserCartByAccountId(accountId);
	}

//	@Override
//	public CartUser update(int proID, int quantity) {
//		Cart cartItem = maps.get(proID);
//		cartItem.setQuantity(quantity);
//		return cartItem;
//	}
}
