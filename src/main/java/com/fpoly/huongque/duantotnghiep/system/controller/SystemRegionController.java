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

import com.fpoly.huongque.duantotnghiep.entity.Regions;
import com.fpoly.huongque.duantotnghiep.service.RegionsService;

@CrossOrigin("*")
@RestController
@RequestMapping("/system/region")
public class SystemRegionController {
	@Autowired
	RegionsService regionsService;

	@GetMapping()
	public List<Regions> getAll() {
		return regionsService.findAll();
	}

	// get one category
	@GetMapping("{id}")
	public ResponseEntity<Regions> getOne(@PathVariable("id") Integer id) {
		Regions regions = regionsService.findById(id);
		return new ResponseEntity<>(regions, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Regions> create(@RequestBody Regions regions) {
		try {
			regionsService.create(regions);
			return new ResponseEntity<>(regions, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	// update category
	@PutMapping("{id}")
	public ResponseEntity<Regions> update(@PathVariable("id") Integer id, @RequestBody Regions regions) {
		try {

			regionsService.create(regions);
			return new ResponseEntity<>(regions, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	// delete account
	@DeleteMapping("{id}")
	public HttpStatus delete(@PathVariable("id") Integer id) {
		try {
			regionsService.deleteById(id);
			return HttpStatus.OK;
		} catch (Exception e) {
			return HttpStatus.BAD_REQUEST;
		}
	}
}
