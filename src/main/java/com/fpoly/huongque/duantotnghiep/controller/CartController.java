package com.fpoly.huongque.duantotnghiep.controller;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fpoly.huongque.duantotnghiep.dao.Cart;
import com.fpoly.huongque.duantotnghiep.entity.Account;
import com.fpoly.huongque.duantotnghiep.entity.Bill;
import com.fpoly.huongque.duantotnghiep.entity.CartUser;
import com.fpoly.huongque.duantotnghiep.entity.Product;
import com.fpoly.huongque.duantotnghiep.entity.Voucher;
import com.fpoly.huongque.duantotnghiep.service.AccountService;
import com.fpoly.huongque.duantotnghiep.service.CartService;
import com.fpoly.huongque.duantotnghiep.service.CartUserService;
import com.fpoly.huongque.duantotnghiep.service.ProductService;

import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping("rest/cart")
@CrossOrigin(origins = "*")
public class CartController {

	@Autowired
	ProductService productService;
	@Autowired
	CartService cartService;

	@Autowired
	AccountService accountService;

	@Autowired
	CartUserService cartUserService;

	String userName = null;
	Integer checkFirst = 0;

	// xem cart
	@GetMapping("view")
	public ResponseEntity<Collection<Cart>> viewCarts(Model model) throws Exception {
		checkCart();
		
		return new ResponseEntity<>(cartService.getAllItems(), HttpStatus.OK);
	}

	// them 1 sp vao cart
	@PostMapping(path =  "add" , consumes = "application/json", produces = "application/json")
	public ResponseEntity<Collection<Cart>> addCart(@RequestBody Cart cart) {
//		Cart product = cart;
		if (cart != null) {
//			Cart item = new Cart();
//			item.setIdProduct(product.getIdProduct());
//			item.setNameProduct(product.getNameProduct());
//			item.setPrice(product.getPrice());
//			item.setQuantity(1);
//			item.setImage(product.getImage());
			cart.setQuantity(1);
			cartService.add(cart);
			saveCart();
			return new ResponseEntity<>(cartService.getAllItems(), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	// lam trong cart
	@GetMapping("clear")
	public ResponseEntity<Collection<Cart>> clearCart() {
		clearCartUser();
		cartService.clear();
		return new ResponseEntity<>(cartService.getAllItems(), HttpStatus.OK);
	}

	// xoa 1 san pham trong cart
	@GetMapping("delete/{id}")
	public ResponseEntity<Collection<Cart>> clearCart(@PathVariable("id") Integer id) {
		cartService.remove(id);
		cartUserService.deleteCartUserProductByIdProduct(id);
		return new ResponseEntity<>(cartService.getAllItems(), HttpStatus.OK);
	}

	// cap nhat so luong sp trong cart
//	@PostMapping("update")
//	public ResponseEntity<Collection<Cart>> update(@RequestBody Map<String, String> json) {
//		Integer id = Integer.valueOf(json.get("id"));
//		Integer quantity = Integer.valueOf(json.get("quantity")) ;
//		cartService.update(id, quantity);
//		saveCart();
//		return new ResponseEntity<>(cartService.getAllItems(), HttpStatus.OK);
//	}

	// lay username cua user dang nhap
	public void checkUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		userName = authentication.getName();
	}

	// kiem tra user da co cart trong database chua
	public void checkCart() {
		try {
			checkUser();
			if (userName != null) {
				List<CartUser> cartUser = cartUserService.findByUserName(userName);
				if (cartUser.size() != 0) {
					if (checkFirst == 0) {
						System.out.println("Khoi phuc cart user");
						for (CartUser p : cartUser) {
							Cart item = new Cart();
							item.setIdProduct(p.getIdProduct());
							item.setNameProduct(p.getName());
							item.setPrice(p.getPrice());
							item.setQuantity(p.getQuantity());
							item.setImage(p.getImage());
							cartService.add(item);
						}
						checkFirst++;
					}
				}
			}
		} catch (Exception e) {
			System.out.println("User chua dang nhap");
		}
	}

	// xoa cart trong database
	public void clearCartUser() {
		try {
			checkUser();
			if (userName != null) {
				Account account = accountService.findByUserNameIgnoreCase(userName);
				cartUserService.deleteCartUserByAccountId(account.getId_Account());
				System.out.println("Xoa cart userId: " + account.getId_Account());
			}
		} catch (Exception e) {
			System.out.println("User chua dang nhap");
		}
	}

	// luu cart vao database
	public void saveCart() {
		try {
			checkUser();
			if (userName != null) {
				Account account = accountService.findByUserNameIgnoreCase(userName);
				Collection<Cart> listProduct = cartService.getAllItems();
				List<CartUser> listCart = null;
				listCart = cartUserService.findByUserName(userName);
				String check = "";
				if (listCart.size() != 0) { // user da co cart trong database
					for (Cart p : listProduct) {
						try {
							check = cartUserService.findByProductName(p.getNameProduct()).getName();
						} catch (Exception e) {
							System.out.println("...");
						}
						if (check != "") {
							cartUserService.deleteCartUserByCartId(cartUserService.findByProductName(p.getNameProduct()).getIdCart());
							check = "";
						}
						CartUser cartUser = new CartUser();
						cartUser.setAccount(account);
						cartUser.setIdProduct(p.getIdProduct());
						cartUser.setName(p.getNameProduct());
						cartUser.setPrice(p.getPrice());
						cartUser.setQuantity(p.getQuantity());
						cartUser.setImage(p.getImage());
						cartUserService.create(cartUser);
						System.out.println("Them san pham vao cart cho user: " + userName + " " + p.getNameProduct());
					}
				} else { // user chua co cart trong database
					for (Cart p : listProduct) {
						CartUser cartUser = new CartUser();
						cartUser.setIdProduct(p.getIdProduct());
						cartUser.setAccount(account);
						cartUser.setName(p.getNameProduct());
						cartUser.setPrice(p.getPrice());
						cartUser.setQuantity(p.getQuantity());
						cartUser.setImage(p.getImage());
						cartUserService.create(cartUser);
						System.out.println("Tao cart moi cho user: " + userName);
					}
				}
			} else {
				System.out.println("User chua dang nhap, khong luu cart vao database");
			}
		} catch (Exception e) {
			System.out.println("User chua dang nhap, khong luu cart vao database");
		}
	}
}
