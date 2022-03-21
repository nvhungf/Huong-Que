package com.fpoly.huongque.duantotnghiep.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fpoly.huongque.duantotnghiep.entity.Category;
import com.fpoly.huongque.duantotnghiep.model.CategoryModel;
import com.fpoly.huongque.duantotnghiep.service.CategoryService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/category")
public class CategoryController {
	@Autowired
	CategoryService categoryService;
	

	@GetMapping()
	public List<CategoryModel> getAll() {
		return categoryService.findAllandSl();
	}
}
