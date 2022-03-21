package com.fpoly.huongque.duantotnghiep.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fpoly.huongque.duantotnghiep.entity.Bill;
import com.fpoly.huongque.duantotnghiep.entity.BillDetail;
import com.fpoly.huongque.duantotnghiep.entity.Product;
import com.fpoly.huongque.duantotnghiep.service.BillDetailService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/bill-detail")
public class BillDetailController {
	@Autowired
	BillDetailService billDetailService;

	@GetMapping()
	public ResponseEntity<List<BillDetail>> getBillDetai(
			@RequestParam(name = "orderCode", required = false, defaultValue = "") String orderCode) {
		try {
			List<BillDetail> billDetail = billDetailService.findByOrderCode(orderCode);
			return new ResponseEntity<>(billDetail, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
