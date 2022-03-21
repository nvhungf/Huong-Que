package com.fpoly.huongque.duantotnghiep.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.fpoly.huongque.duantotnghiep.entity.Blogs;
import com.fpoly.huongque.duantotnghiep.service.BlogsService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/blogs")
public class BlogsController {

	@Autowired
	BlogsService blogsService;
	
	@GetMapping("/all")
	public List<Blogs> All() {
	
		
		return blogsService.findAll();
	}
	
	@GetMapping("{id}")
	public Optional<Blogs> id(@PathVariable("id") Long id) {
	
		
		return blogsService.findById(id);
	}
}
