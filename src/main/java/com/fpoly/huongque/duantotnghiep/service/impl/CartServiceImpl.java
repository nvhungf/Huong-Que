package com.fpoly.huongque.duantotnghiep.service.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import com.fpoly.huongque.duantotnghiep.dao.Cart;
import com.fpoly.huongque.duantotnghiep.service.CartService;



@SessionScope
@Service
public class CartServiceImpl implements CartService{
	Map<Integer, Cart> maps = new HashMap<>();
	

	@Override
	public void add(Cart item) {
		Cart cartItem = maps.get(item.getIdProduct());
		if (cartItem == null) {
			maps.put(item.getIdProduct(), item);
		}else {
			cartItem.setQuantity(cartItem.getQuantity() + 1);
		}
	}
	
	@Override
	public void remove(int id) {
		maps.remove(id);
	}
	
	@Override
	public Cart update(int proID, int quantity) {
		Cart cartItem = maps.get(proID);
		cartItem.setQuantity(quantity);
		return cartItem;
	}
	
	@Override
	public void clear() {
		maps.clear();
	}
	
	@Override
	public Collection<Cart> getAllItems(){
		return maps.values();
	}
	
	@Override
	public int getCount() {
		return maps.values().size();
	}
	
	@Override
	public double getAmount() {
		return maps.values().stream()
				.mapToDouble(item -> item.getQuantity() * item.getPrice())
				.sum();
	}
}
