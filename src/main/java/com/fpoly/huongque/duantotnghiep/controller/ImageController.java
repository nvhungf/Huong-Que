package com.fpoly.huongque.duantotnghiep.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fpoly.huongque.duantotnghiep.entity.Image;
import com.fpoly.huongque.duantotnghiep.service.ImageService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/image")	
public class ImageController {
	@Autowired
	ImageService imageService;
	
	@GetMapping()
	public List<Image> getAll() {
		return imageService.findAll();
	}
	
	@GetMapping("product-id")
	public List<Image> GetImageByProduct(@RequestParam(name = "id_product", required = false, defaultValue = "") Integer id_product) {
		return imageService.GetImageByProduct(id_product);
	}
	
}
