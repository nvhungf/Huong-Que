package com.fpoly.huongque.duantotnghiep.system.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fpoly.huongque.duantotnghiep.entity.Image;
import com.fpoly.huongque.duantotnghiep.entity.Product;
import com.fpoly.huongque.duantotnghiep.service.ImageService;
import com.fpoly.huongque.duantotnghiep.service.ProductService;

@CrossOrigin("*")
@RestController
@RequestMapping("/system/image")
public class SystemImageController {
	@Autowired
	ImageService imageService;

	@Autowired
	ProductService productService;

	@GetMapping()
	public List<Image> getAll() {
		return imageService.findAll();
	}

	@GetMapping("product-id")
	public List<Image> GetImageByProduct(
			@RequestParam(name = "id_product", required = false, defaultValue = "") Integer id_product) {
		return imageService.GetImageByProduct(id_product);
	}

	@PostMapping
	public ResponseEntity<Image> create(@RequestBody Image image,
			@RequestParam(name = "id_product", required = false, defaultValue = "") Integer id_product) {
		try {
			Product product = productService.findById(id_product);
			image.setProduct(product);
			Date date = new Date();
			image.setDate_Upload(date);
			imageService.create(image);
			return new ResponseEntity<>(image, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("{id}")
	public ResponseEntity<Image> update(@PathVariable("id") Integer id, @RequestBody Image image) {
		try {
			imageService.create(image);
			return new ResponseEntity<>(image, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("{id}")
	public HttpStatus delete(@PathVariable("id") Integer id) {
		try {
			imageService.deleteById(id);
			return HttpStatus.OK;
		} catch (Exception e) {
			return HttpStatus.BAD_REQUEST;
		}
	}

}
