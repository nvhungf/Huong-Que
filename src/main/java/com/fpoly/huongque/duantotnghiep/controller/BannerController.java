package com.fpoly.huongque.duantotnghiep.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fpoly.huongque.duantotnghiep.entity.Banner;
import com.fpoly.huongque.duantotnghiep.entity.Voucher;
import com.fpoly.huongque.duantotnghiep.service.BannerService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/banner")
public class BannerController {
	@Autowired
	BannerService bannerService;
	
	//lay all banner
	@GetMapping("")
	public ResponseEntity<List<Banner>> getAllBanner() {
		try {
			return new ResponseEntity<>(bannerService.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// lay banner theo id
	@GetMapping("{id}")
	public ResponseEntity<Banner> getOneBanner(@PathVariable("id") Integer id) {
		try {
			return new ResponseEntity<>(bannerService.findById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	
}
