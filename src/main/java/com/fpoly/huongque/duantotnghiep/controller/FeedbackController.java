package com.fpoly.huongque.duantotnghiep.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fpoly.huongque.duantotnghiep.entity.Account;
import com.fpoly.huongque.duantotnghiep.entity.Bill;
import com.fpoly.huongque.duantotnghiep.entity.BillDetail;
import com.fpoly.huongque.duantotnghiep.entity.Feedback;
import com.fpoly.huongque.duantotnghiep.entity.Product;
import com.fpoly.huongque.duantotnghiep.service.AccountService;
import com.fpoly.huongque.duantotnghiep.service.BillService;
import com.fpoly.huongque.duantotnghiep.service.FeedbackService;
import com.fpoly.huongque.duantotnghiep.service.ProductService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/feedback")
public class FeedbackController {
	@Autowired
	FeedbackService feedbackService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	AccountService accountService;

	@Autowired
	BillService billService;

	@GetMapping()
	public List<Feedback> getAll() {
		return feedbackService.findAll();
	}
	
	//feedback theo id product
	@PostMapping(path = "/create/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity createFeedback(@RequestBody Feedback feedback,
			@PathVariable("id") Integer id) {
		
		//kiem tra user login chua
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		if (userName == "anonymousUser") {
			return ResponseEntity
		            .status(HttpStatus.UNAUTHORIZED)
		            .body("Chưa đăng nhập");
		}
		
		//check so sao
		if (feedback.getStar()<1 || feedback.getStar()>5) {
			return ResponseEntity
		            .status(HttpStatus.NOT_ACCEPTABLE)
		            .body("Số sao phải trong khoảng 1 - 5");
		}
		
		Account account = accountService.findByUserNameIgnoreCase(userName);
		Product p = productService.findById(id);
		
		List<Bill> bill = billService.getPurchaseHistory(account.getId_Account());
		for (Bill b: bill) {
			List<BillDetail> listBillDetail = b.getBillDetail();
				for (BillDetail billDetail: listBillDetail) {
					if (billDetail.getProduct().getIdProduct().equals(id)) { //check user da mua sp chua
						
						List<Feedback> listFeedback = feedbackService.findByIdAccount(account.getId_Account());
						for (Feedback fb:listFeedback) {
							if (fb.getProduct().getIdProduct().equals(id)) { //check da danh gia chua
								return ResponseEntity
							            .status(HttpStatus.NOT_ACCEPTABLE)
							            .body("User đã đánh giá sản phẩm này");
							}
						}
						
						feedback.setProduct(p);
						feedback.setAccount(account);
						return new ResponseEntity<>(feedbackService.save(feedback), HttpStatus.OK);
					}
				}
		}
		
		return ResponseEntity
	            .status(HttpStatus.NOT_ACCEPTABLE)
	            .body("User chưa mua sản phẩm này");
	}
}
