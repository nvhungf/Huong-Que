package com.fpoly.huongque.duantotnghiep.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fpoly.huongque.duantotnghiep.entity.Regions;
import com.fpoly.huongque.duantotnghiep.service.RegionsService;


@CrossOrigin("*")
@RestController
@RequestMapping("/rest/region")
public class RegionsController {
	@Autowired
	RegionsService regionsService;
	
	@GetMapping()
	public List<Regions> getAll() {
		return regionsService.findAll();
	}
}
