package com.fpoly.huongque.duantotnghiep.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Producer;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fpoly.huongque.duantotnghiep.service.AccountService;
import com.fpoly.huongque.duantotnghiep.service.LikeService;
import com.fpoly.huongque.duantotnghiep.service.ProductService;
import com.fpoly.huongque.duantotnghiep.entity.Account;
import com.fpoly.huongque.duantotnghiep.entity.Likes;
import com.fpoly.huongque.duantotnghiep.entity.Product;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/likes")
public class LikeController {
	@Autowired
	LikeService likeService;

	@Autowired
	AccountService accountService;

	@Autowired
	ProductService productService;

	@GetMapping()
	public List<Likes> getAll() {
		return likeService.findAll();
	}

	// like/unlike product
	@GetMapping("product/{id}")
	public HttpStatus like(@PathVariable("id") Integer id) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		System.out.println("u" + userName);
		Account account = accountService.findByUserNameIgnoreCase(userName);
		Product product = productService.findById(id);
		// kiem tra user da dang nhap chua, neu chua ko cho like
		if (userName == "anonymousUser") {
			return HttpStatus.UNAUTHORIZED;
		}
		
		List<Likes> list = null;
		list = likeService.findByIdAccount(account.getId_Account());
		for (Likes l:list) {
			System.out.println(l.getIdAccount() + " " + l.getProduct().getIdProduct());
			if ( l.getProduct().getIdProduct().equals(id)) { //kiem tra neu da like thi thanh unlike
				likeService.deleteById(l.getIdLike());
				return HttpStatus.OK;
			}
		}
		Likes likes = new Likes();
		likes.setIdAccount(account.getId_Account());
		likes.setProduct(product);
		likes.setIsLike(true);
		likeService.create(likes);
	return HttpStatus.CREATED;

	}
}
