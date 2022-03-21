package com.fpoly.huongque.duantotnghiep.system.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.fpoly.huongque.duantotnghiep.entity.Category;
import com.fpoly.huongque.duantotnghiep.service.CategoryService;

@CrossOrigin("*")
@RestController
@RequestMapping("/system/category")
public class SystemCategoryController {
	@Autowired
	CategoryService categoryService;

	@GetMapping()
	public List<Category> getAll() {
		return categoryService.findAll();
	}

	// get one category
	@GetMapping("{id}")
	public ResponseEntity<Category> getOne(@PathVariable("id") Integer id) {
		Category category = categoryService.findById(id);
		return new ResponseEntity<>(category, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Category> create(@RequestBody Category category) {
		try {
			categoryService.create(category);
			return new ResponseEntity<>(category, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	// update category
	@PutMapping("{id}")
	public ResponseEntity<Category> update(@PathVariable("id") Integer id, @RequestBody Category category) {
		try {

			categoryService.create(category);
			return new ResponseEntity<>(category, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	// delete account
	@DeleteMapping("{id}")
	public HttpStatus delete(@PathVariable("id") Integer id) {
		try {
			categoryService.deleteById(id);
			return HttpStatus.OK;
		} catch (Exception e) {
			return HttpStatus.BAD_REQUEST;
		}
	}
}
