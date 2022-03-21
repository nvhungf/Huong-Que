package com.fpoly.huongque.duantotnghiep.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fpoly.huongque.duantotnghiep.entity.Voucher;
import com.fpoly.huongque.duantotnghiep.service.VoucherService;

@CrossOrigin("*")
@RestController
@RequestMapping("/system/voucher")
public class SystemVoucherController {
	@Autowired
	VoucherService voucherService;

	@GetMapping("")
	public ResponseEntity<List<Voucher>> getAllVoucher() {
		try {
			return new ResponseEntity<>(voucherService.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// lay voucher theo id
	@GetMapping("{id}")
	public ResponseEntity<Voucher> getOneVoucher(@PathVariable("id") String id) {
		try {
			return new ResponseEntity<>(voucherService.findById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// tao moi vouchere
	@PostMapping(path = "/create", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Voucher> createVoucher(@RequestBody Voucher voucher) {
		return new ResponseEntity<>(voucherService.save(voucher), HttpStatus.OK);
	}

	// xoa voucher
	@GetMapping("delete/{id}")
	public ResponseEntity<Voucher> deleteOneVoucher(@PathVariable("id") String id) {
		try {

			voucherService.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);

		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// update voucher
	@PostMapping(path = "/update", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Voucher> updateVoucher(@RequestBody Voucher voucher) {
		voucherService.update(voucher);
		return new ResponseEntity<>(voucher, HttpStatus.OK);
	}
}
