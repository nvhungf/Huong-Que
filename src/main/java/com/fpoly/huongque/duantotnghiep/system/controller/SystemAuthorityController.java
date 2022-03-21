package com.fpoly.huongque.duantotnghiep.system.controller;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fpoly.huongque.duantotnghiep.entity.Authority;
import com.fpoly.huongque.duantotnghiep.service.AuthorityService;

@CrossOrigin("*")
@RestController
@RequestMapping("/admin/authorities")
public class SystemAuthorityController {
	@Autowired
	AuthorityService authorityService;

	@GetMapping
	public List<Authority> findAll(@RequestParam("admin") Optional<Boolean> admin) {
		if (admin.orElse(false)) {
			return authorityService.findAuthoritiesOfAdministrators();
		}
		return authorityService.findAll();
	}

	@PostMapping
	public ResponseEntity<Authority> post(@RequestBody Authority auth) {
		try {
			Authority authority = authorityService.create(auth);
			return new ResponseEntity<>(authority, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("{id}")
	public HttpStatus delete(@PathVariable("id") Integer id) {
		try {
			authorityService.delete(id);
			return HttpStatus.OK;
		} catch (Exception e) {
			return HttpStatus.BAD_REQUEST;
		}
	}

}
