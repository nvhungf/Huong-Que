package com.fpoly.huongque.duantotnghiep.controller;

import com.fpoly.huongque.duantotnghiep.constant.Constant;
import com.fpoly.huongque.duantotnghiep.constant.EmailTemplate;
import com.fpoly.huongque.duantotnghiep.constant.OrderEnum;
import com.fpoly.huongque.duantotnghiep.dto.MailDTO;
import com.fpoly.huongque.duantotnghiep.entity.*;
import com.fpoly.huongque.duantotnghiep.service.*;
import com.fpoly.huongque.duantotnghiep.utils.CreateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/bill")
public class BillController {
	@Autowired
	BillService billService;

	@Autowired
	BillDetailService billDetailService;

	@Autowired
	ProductService productService;

	@Autowired
	CartService cartService;

	@Autowired
	AccountService accountService;

	@Autowired
	CartUserService cartUserService;

	@Autowired
	VoucherService voucherService;

	@Autowired
	private MailService mailService;

	@Transactional
	@PostMapping(path = "/create", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Bill> createOrder(@RequestBody Bill bill, Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		Account account = accountService.findByUserNameIgnoreCase(userName);

		// kiem tra user da dang nhap chua, neu chua ko cho tao bill
		if (userName == "anonymousUser") {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

		bill.setOrderStatus(OrderEnum.WAIT_APPROVE.getValue());
		bill.setAccount(account);
		Date date = java.sql.Date.valueOf(java.time.LocalDate.now());
		bill.setDateCreate(date);
		bill.setDateSuccess(date);

		// tạo mã đơn hàng
		String orderCode = CreateUtils.createOrderCode(Constant.BEGIN_CREATE);
		bill.setOrder_Code(orderCode);
		Bill b = billService.create(bill);

		// luu thong tin bill detail
		List<BillDetail> listProduct = bill.getBillDetail();
		for (BillDetail billDetail : listProduct) {

			Product pro = productService.findById(billDetail.getProduct().getIdProduct());
			billDetail.setProduct(billDetail.getProduct());
			billDetail.setIdBill(b.getIdBill());
			billDetail.setPrice(billDetail.getPrice());
			billDetail.setQuantity(billDetail.getQuantity());
			billDetailService.create(billDetail);

			// tru so luong product trong kho
			pro.setQuantity(pro.getQuantity() - billDetail.getQuantity());
			productService.update(pro);
		}

		model.addAttribute("username", account.getUser_Name());
		model.addAttribute("orderCode", orderCode);

		MailDTO mailDTO = new MailDTO();
		mailDTO.setTitle("[Hương Quê-Cảm ơn] Bạn đã đặt hàng với mã số: " + orderCode);
		mailDTO.setEmailTemplate(EmailTemplate.ORDER_NOTICE);
		mailDTO.setSendTo(bill.getEmailReceiver());

		try {
			this.mailService.sendMail(model, mailDTO);
		} catch (Exception exception) {
			return new ResponseEntity<>(bill, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(bill, HttpStatus.OK);
	}

	// get purchase history by id_account

	@GetMapping("/purchase-history")
	public ResponseEntity<List<Bill>> purchase_history() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		Account account = accountService.findByUserNameIgnoreCase(userName);
		if (userName == "anonymousUser") {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		} else {
			Integer id_Account = account.getId_Account();
			List<Bill> bill = billService.getPurchaseHistory(id_Account);
			return new ResponseEntity<>(bill, HttpStatus.OK);
		}

	}

	// cập nhật đã huỷ đơn hàng 
	@PostMapping("/cancel/{orderCode}")
	public ResponseEntity cancel(@PathVariable("orderCode") String orderCode, @RequestParam String cancellationReason, Model model) {
		Bill bill = billService.getBill(orderCode);
		Account account = bill.getAccount();
		try {
			bill.setOrderStatus(OrderEnum.CANCEL.getValue());
			bill.setCancellationReason(cancellationReason);
			billService.create(bill);

			model.addAttribute("username", account.getUser_Name());
			model.addAttribute("orderCode", bill.getOrder_Code());

			MailDTO mailDTO = new MailDTO();
			mailDTO.setSendTo(account.getEmail());
			mailDTO.setTitle("[Hương Quê] Đơn hàng " + bill.getOrder_Code() + " của bạn đã được huỷ thành công.");
			mailDTO.setEmailTemplate(EmailTemplate.CANCEL_ORDER);

			String message = this.mailService.sendMail(model, mailDTO);

			return ResponseEntity.status(HttpStatus.CREATED)
					.body(new String[] {message});

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new String[] {e.getMessage()});
		}
	}

// xem tổng đơn hàng và chi tiêu (đơn đã giao order_Status = 3 ) của user
	@GetMapping("/statistics-by-account")
	public ResponseEntity<StatisticByAccount> getTotalByAccount() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		Account account = accountService.findByUserNameIgnoreCase(userName);
		if (userName == "anonymousUser") {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		} else {
			Integer id_Account = account.getId_Account();
			StatisticByAccount statisticByAccount = billService.getStatisticByAccount(id_Account);
			return new ResponseEntity<>(statisticByAccount, HttpStatus.OK);
		}

	}

	// xem bill theo order_Code
	@GetMapping("/get")
	public ResponseEntity<Bill> getBill(@RequestParam("order_Code") String order_Code) {
		Bill bill = billService.getBill(order_Code);
		return new ResponseEntity<>(bill, HttpStatus.OK);
	}

	@GetMapping("/points/{id}")
	public Map<String, Integer> points(@PathVariable("id") Integer id) {
		Account account = accountService.findById(id);

		return Collections.singletonMap("points", account.getPoints());
	}

	@PostMapping("/change-status")
	public ResponseEntity changeStatus(@RequestBody Bill bill) {
		try {
			Bill b = this.billService.getBill(bill.getOrder_Code());
			b.setOrderStatus(bill.getOrderStatus());
			this.billService.create(b);
			return ResponseEntity.status(HttpStatus.OK).body(b);
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex);
		}
	}

}
