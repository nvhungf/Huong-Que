package com.fpoly.huongque.duantotnghiep.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
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

import com.fpoly.huongque.duantotnghiep.entity.Bill;
import com.fpoly.huongque.duantotnghiep.entity.Product;
import com.fpoly.huongque.duantotnghiep.service.ProductService;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/product")
public class ProductController {
	@Autowired
	ProductService productService;

	// get all product
	@GetMapping()
	public List<Product> getAll(HttpServletRequest httpServletRequest) {
		return productService.getAllProductEnabled();
	}

	// get top 10 product ban chay :v
	@GetMapping("/top-10")
	public List<Product> top10() {
		return productService.findTop10();
	}
	
	// get top 5 product trend :v
	@GetMapping("/top-5-trend")
	public List<Product> top5Trend() {
		return productService.findTop5Trending();
	}

	// get one product
	@GetMapping("{id}")
	public ResponseEntity<Product> getOneProduct(@PathVariable("id") Integer id) {
		try {
			Product product = productService.findById(id);
			return new ResponseEntity<>(product, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// get product by category
	@GetMapping("/product-by-category")
	public ResponseEntity<List<Product>> getByCategory(
			@RequestParam(name = "category", required = false, defaultValue = "") String cid) {
		try {
			List<Product> product = productService.findByCategory(cid);
			return new ResponseEntity<>(product, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// get product by region
	@GetMapping("/product-by-region")
	public ResponseEntity<List<Product>> getByRegion(
			@RequestParam(name = "region", required = false, defaultValue = "") String rid) {
		try {
			List<Product> product = productService.findByRegion(rid);
			return new ResponseEntity<>(product, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// search search theo name, description
	@GetMapping("/search")
	public ResponseEntity<List<Product>> listProductsSearch(
			@RequestParam(name = "keyword", required = false, defaultValue = "") String keyword) {
		List<Product> listProducts = productService.search(keyword);
		return new ResponseEntity<>(listProducts, HttpStatus.OK);
	}
	@GetMapping("/likes/{id}")
	public List<Product> productForLikes(@PathVariable("id") String id){
		 List<Product> list  = productService.findByLikes(id);
		return list;
		
	}
}
