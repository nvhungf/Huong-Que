package com.fpoly.huongque.duantotnghiep.system.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fpoly.huongque.duantotnghiep.entity.Product;
import com.fpoly.huongque.duantotnghiep.service.ProductService;

@CrossOrigin("*")
@RestController
@RequestMapping("/system/product")
public class SystemProductController {
	@Autowired
	ProductService productService;

	// get all product
	@GetMapping()
	public List<Product> getAll() {
		return productService.findAll();
	}

	// get top 10 product ban chay :v
	@GetMapping("/top-10")
	public List<Product> top10() {
		return productService.findTop10();
	}

	// get one product
	@GetMapping("{id}")
	public Product getOneProduct(@PathVariable("id") Integer id) {
		return productService.findById(id);
	}

	// create product
	@PostMapping
	public ResponseEntity<Product> create(@RequestBody Product product) {
		try {
			Date date = java.sql.Date.valueOf(java.time.LocalDate.now());
			product.setDateCreate(date);
			productService.create(product);
			return new ResponseEntity<>(product, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	// upadate product
	@PutMapping("{id}")
	public ResponseEntity<Product> update(@PathVariable("id") Integer id, @RequestBody Product product) {
		try {
			productService.create(product);
			return new ResponseEntity<>(product, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	// delete product
	@DeleteMapping("{id}")
	public ResponseEntity<Product> delete(@PathVariable("id") Integer id, Model model) {
		try {
			productService.delete(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}
}
