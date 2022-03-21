package com.fpoly.huongque.duantotnghiep.system.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import com.fpoly.huongque.duantotnghiep.entity.Account;
import com.fpoly.huongque.duantotnghiep.entity.Authority;
import com.fpoly.huongque.duantotnghiep.entity.Role;
import com.fpoly.huongque.duantotnghiep.service.AccountService;
import com.fpoly.huongque.duantotnghiep.service.AuthorityService;
import com.fpoly.huongque.duantotnghiep.service.RoleService;

@CrossOrigin("*")
@RestController
@RequestMapping("/admin/accounts")
public class SystemAccountController {
	@Autowired
	AccountService accountService;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	AuthorityService authorityService;

	@Autowired
	RoleService roleService;

	// get all account
	@GetMapping()
	public List<Account> getAll() {
		return accountService.findAll();
	}

	// get one account
	@GetMapping("{id}")
	public ResponseEntity<Account> getOne(@PathVariable("id") Integer id) {
		Account account = accountService.findById(id);
		return new ResponseEntity<>(account, HttpStatus.OK);
	}

	// create account nhan vien
	@PostMapping("/admin/create")
	public ResponseEntity<Account> addAccount(@RequestBody Account account,
			@RequestParam(name = "id_role", required = false, defaultValue = "") String id_role) {
		try {
			String passPE = passwordEncoder.encode(account.getPassword());
			account.setPassword(passPE);
			account.setPoints(0);
			Date date = java.sql.Date.valueOf(java.time.LocalDate.now());
			account.setCreate_Date(date);
			// cap quyen
			Role role = roleService.findById(id_role);
			Authority authority = new Authority();
			authority.setAccount(account);
			authority.setRole(role);
			accountService.create(account);
			authorityService.create(authority);
			return new ResponseEntity<>(account, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

	// update account
	@PutMapping("{id}")
	public ResponseEntity<Account> update(@PathVariable("id") Integer id, @RequestBody Account account) {
		try {
			String passPE = passwordEncoder.encode(account.getPassword());
			account.setPassword(passPE);
			accountService.create(account);
			return new ResponseEntity<>(account, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	// delete account
	@DeleteMapping("{id}")
	public HttpStatus delete(@PathVariable("id") Integer id) {
		try {
			accountService.delete(id);
			return HttpStatus.OK;
		} catch (Exception e) {
			return HttpStatus.BAD_REQUEST;
		}
	}

	// thống kê //

	// top 10 account mua hàng nhiều trong ngày hôm nay
	@GetMapping("/Get-Top-10-Account-Day")
	public List<Account> GetTop10AccountDay() {
		return accountService.GetTop10AccountDay();
	}

	// top 10 account mua hàng nhiều trong tháng
	@GetMapping("/Get-Top-10-Account-Month")
	public List<Account> GetTop10AccountMonth() {
		return accountService.GetTop10AccountMonth();
	}

	// top 10 account mua hàng nhiều trong năm
	@GetMapping("/Get-Top-10-Account-Year")
	public List<Account> GetTop10AccountYear() {
		return accountService.GetTop10AccountYear();
	}

}
