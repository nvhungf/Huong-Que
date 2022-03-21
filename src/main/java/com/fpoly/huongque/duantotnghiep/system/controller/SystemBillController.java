package com.fpoly.huongque.duantotnghiep.system.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.Model;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fpoly.huongque.duantotnghiep.dto.MailDTO;
import com.fpoly.huongque.duantotnghiep.entity.Account;
import com.fpoly.huongque.duantotnghiep.entity.Bill;
import com.fpoly.huongque.duantotnghiep.reports.BillExcelExporter;
import com.fpoly.huongque.duantotnghiep.service.AccountService;
import com.fpoly.huongque.duantotnghiep.service.BillService;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@CrossOrigin("*")
@RestController
@RequestMapping("/system/bill")
public class SystemBillController {
	@Autowired
	BillService billService;
	@Autowired
	AccountService accountService;
	@Autowired
	private JavaMailSender sender;

	@Autowired
	private Configuration config;

	// get all bill
	@GetMapping()
	public List<Bill> getAll() {
		return billService.findAll();
	}

	// get one bill
	@GetMapping("{id}")
	public ResponseEntity<Bill> getOne(@PathVariable("id") Integer id) {
		Bill bill = billService.findById(id);
		return new ResponseEntity<>(bill, HttpStatus.OK);
	}

	// xem bill theo order_Code
	@GetMapping("/get")
	public ResponseEntity<Bill> getBill(@RequestParam("order_Code") String order_Code) {
		Bill bill = billService.getBill(order_Code);
		return new ResponseEntity<>(bill, HttpStatus.OK);
	}

	// get bill by date
	@GetMapping("/bill-by-date")
	public ResponseEntity<List<Bill>> getBillByDate(@RequestParam("dateFrom") Date dateFrom,
			@RequestParam("dateTo") Date dateTo) {
		List<Bill> bill = billService.getBillByDate(dateFrom, dateTo);
		;
		return new ResponseEntity<>(bill, HttpStatus.OK);
	}

	// update bill
	@PutMapping("update")
	public ResponseEntity<Bill> update(@RequestBody Bill bill) {
		try {
			billService.create(bill);
			return new ResponseEntity<>(bill, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	// cập nhật đã duyệt đơn hàng
	@PostMapping("/approve/{id}")
	public ResponseEntity approve(@PathVariable("id") Integer id, Model models, Map<String, Object> model) {
		MailDTO response = new MailDTO();
		Bill bill = billService.findById(id);
		Account account = accountService.GetAccountByBill(id);
		try {
			bill.setOrderStatus(2);
			billService.create(bill);
			MimeMessage message = sender.createMimeMessage();
			try {
				// set mediaType
				MimeMessageHelper helper = new MimeMessageHelper(message,
						MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
				models.addAttribute("username", account.getUser_Name());
				models.addAttribute("orderCode", bill.getOrder_Code());
				Template t = config.getTemplate("approve-order.ftl");
				String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);

				helper.setTo(account.getEmail());
				helper.setText(html, true);
				helper.setSubject("[Hương Quê] Đơn hàng " + bill.getOrder_Code()
						+ " đã được duyệt và sẽ được bàn giao cho đơn vị vận chuyển.");
				helper.setFrom("Huong Que <suport@huongque.vn>");
				sender.send(message);
				response.setMessage("mail send to : " + account.getEmail());
				response.setStatus(Boolean.TRUE);
				return ResponseEntity.status(HttpStatus.CREATED)
						.body("Cập nhật đã duyệt cho đơn hàng : " + bill.getIdBill() + " thành công.");
			} catch (MessagingException | IOException | TemplateException e) {
				response.setMessage("Mail Sending failure : " + e.getMessage());
				response.setStatus(Boolean.FALSE);
				models.addAttribute("message", "Lỗi gửi email.");
				return ResponseEntity.status(HttpStatus.CONFLICT).body("Lỗi Gửi Mail");
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	// cập nhật đã giao đơn hàng
	@PostMapping("/delivered/{id}")
	public ResponseEntity delivered(@PathVariable("id") Integer id, Model models, Map<String, Object> model) {
		MailDTO response = new MailDTO();
		Bill bill = billService.findById(id);
		Account account = accountService.GetAccountByBill(id);
		try {
			bill.setOrderStatus(3);
			billService.create(bill);
			MimeMessage message = sender.createMimeMessage();
			try {
				// set mediaType
				MimeMessageHelper helper = new MimeMessageHelper(message,
						MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
				models.addAttribute("username", account.getUser_Name());
				models.addAttribute("orderCode", bill.getOrder_Code());
				Template t = config.getTemplate("delivered-order.ftl");
				String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);

				helper.setTo(account.getEmail());
				helper.setText(html, true);
				helper.setSubject("[Hương Quê] Đơn hàng " + bill.getOrder_Code() + " đã được giao thành công.");
				helper.setFrom("Huong Que <suport@huongque.vn>");
				sender.send(message);
				response.setMessage("mail send to : " + account.getEmail());
				response.setStatus(Boolean.TRUE);
				return ResponseEntity.status(HttpStatus.CREATED)
						.body("Cập nhật đã giao cho đơn hàng : " + bill.getIdBill() + " thành công.");
			} catch (MessagingException | IOException | TemplateException e) {
				response.setMessage("Mail Sending failure : " + e.getMessage());
				response.setStatus(Boolean.FALSE);
				models.addAttribute("message", "Lỗi gửi email.");
				return ResponseEntity.status(HttpStatus.CONFLICT).body("Lỗi Gửi Mail");
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	// cập nhật đã huỷ đơn hàng
	@PostMapping("/cancel/{id}")
	public ResponseEntity cancel(@PathVariable("id") Integer id, @RequestParam String cancellationReason, Model models,
			Map<String, Object> model) {
		MailDTO response = new MailDTO();
		Bill bill = billService.findById(id);
		Account account = accountService.GetAccountByBill(id);
		try {
			bill.setOrderStatus(4);
			bill.setCancellationReason(cancellationReason);
			billService.create(bill);
			MimeMessage message = sender.createMimeMessage();
			try {
				// set mediaType
				MimeMessageHelper helper = new MimeMessageHelper(message,
						MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
				models.addAttribute("username", account.getUser_Name());
				models.addAttribute("orderCode", bill.getOrder_Code());
				Template t = config.getTemplate("cancel-order.ftl");
				String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);

				helper.setTo(account.getEmail());
				helper.setText(html, true);
				helper.setSubject("[Hương Quê] Đơn hàng " + bill.getOrder_Code() + " của bạn đã bị huỷ.");
				helper.setFrom("Huong Que <suport@huongque.vn>");
				sender.send(message);
				response.setMessage("mail send to : " + account.getEmail());
				response.setStatus(Boolean.TRUE);
				return ResponseEntity.status(HttpStatus.CREATED)
						.body("Cập nhật đã huỷ cho đơn hàng : " + bill.getIdBill() + " thành công.");
			} catch (MessagingException | IOException | TemplateException e) {
				response.setMessage("Mail Sending failure : " + e.getMessage());
				response.setStatus(Boolean.FALSE);
				models.addAttribute("message", "Lỗi gửi email.");
				return ResponseEntity.status(HttpStatus.CONFLICT).body("Lỗi Gửi Mail");
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/excel")
	public void exportToExcel(HttpServletResponse response) throws IOException {
		response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		String currentDateTime = dateFormatter.format(new java.util.Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=Bills_" + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);

		List<Bill> listBill = billService.findAll();

		BillExcelExporter excelExporter = new BillExcelExporter(listBill);

		excelExporter.export(response);
	}

	@GetMapping("/excel/-by-date")
	public void exportToExcelDate(HttpServletResponse response, @RequestParam("dateFrom") Date dateFrom,
			@RequestParam("dateTo") Date dateTo) throws IOException {
		response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new java.util.Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=Bills_" + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);
		List<Bill> bill = billService.getBillByDate(dateFrom, dateTo);

		BillExcelExporter excelExporter = new BillExcelExporter(bill);

		excelExporter.export(response);
	}

}
