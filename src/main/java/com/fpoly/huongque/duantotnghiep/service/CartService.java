package com.fpoly.huongque.duantotnghiep.service;

import java.util.Collection;

import com.fpoly.huongque.duantotnghiep.dao.Cart;


public interface CartService {

	double getAmount();

	int getCount();

	Collection<Cart> getAllItems();

	void clear();

	Cart update(int proID, int quantity);

	void remove(int id);

	void add(Cart item);

}
