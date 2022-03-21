package com.fpoly.huongque.duantotnghiep.controller;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fpoly.huongque.duantotnghiep.constant.EmailTemplate;
import com.fpoly.huongque.duantotnghiep.dao.AuthorityDao;
import com.fpoly.huongque.duantotnghiep.dto.MailDTO;
import com.fpoly.huongque.duantotnghiep.entity.Account;
import com.fpoly.huongque.duantotnghiep.entity.Authority;
import com.fpoly.huongque.duantotnghiep.entity.Role;
import com.fpoly.huongque.duantotnghiep.entity.Voucher;
import com.fpoly.huongque.duantotnghiep.service.AccountService;
import com.fpoly.huongque.duantotnghiep.service.AuthorityService;
import com.fpoly.huongque.duantotnghiep.service.MailService;
import com.fpoly.huongque.duantotnghiep.service.VoucherService;
import com.fpoly.huongque.duantotnghiep.service.impl.OtpService;
import com.fpoly.huongque.duantotnghiep.utils.CreateUtils;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/accounts")
public class AccountController {
	@Autowired
	AccountService accountService;
	@Autowired
	VoucherService voucherService;
	@Autowired
	AuthorityService authorityService;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	MailService mailService;
	
	@Autowired
	OtpService otpService;

	@Autowired
	AuthorityDao authorityDao;

	// lấy thông tin account đăng nhập
	@GetMapping("/info")
	public ResponseEntity<Account> info() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		Account account = accountService.findByUserNameIgnoreCase(userName);
		if (userName == "anonymousUser") {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<>(account, HttpStatus.OK);
	}

	// lấy thông tin auth
	@GetMapping("/auth")
	public ResponseEntity<List<Authority>> auth() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		Account account = accountService.findByUserNameIgnoreCase(userName);
		if (userName == "anonymousUser") {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		List<Authority> auth = authorityService.getAuth(account.getId_Account());
		return new ResponseEntity<>(auth, HttpStatus.OK);
	}

	// registration
	@PostMapping
	@Transactional
	public ResponseEntity registration(@RequestBody Account account, Model model) {
		Account existingUsersEmail = accountService.findByEmailIgnoreCase(account.getEmail());
		Account existingUsersPhone = accountService.findByPhoneIgnoreCase(account.getPhone_Number());
		Account existingUsersUserName = accountService.findByUserNameIgnoreCase(account.getUser_Name());
		if (existingUsersUserName != null) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("username đã tồn tại !");
		}

		if (existingUsersEmail != null) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Email đã tồn tại !");
		}

		if (existingUsersPhone != null) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Số điện thoại đã tồn tại !");
		}
		String passPE = passwordEncoder.encode(account.getPassword());
		account.setPassword(passPE);
		Date date = java.sql.Date.valueOf(java.time.LocalDate.now());
		account.setCreate_Date(date);
		account.setPoints(0);
		account.set_Enabled(Boolean.TRUE);
		accountService.create(account);
		// tạo voucher
		String code = CreateUtils.createCode();
		Voucher voucher = new Voucher();
		voucher.setIdVoucher(code);
		voucher.setNameVoucher("voucher tài khoản : " + account.getUser_Name());
		voucher.setDateStart(date);
		voucher.setIsEnabled(true);
		voucher.setPercentVoucher(25.0);
		voucherService.save(voucher);

		Authority authority = new Authority();
		authority.setAccount(account);
		authority.setRole(new Role(com.fpoly.huongque.duantotnghiep.constant.Role.USER.getRole()));
		this.authorityDao.save(authority);

		try {
			model.addAttribute("username", account.getUser_Name());
			model.addAttribute("voucher", voucher.getIdVoucher());
			// set mediaType
			MailDTO mail = new MailDTO();
			mail.setTitle("[Hương Quê] Chào mừng thành viên mới-Tặng bạn voucher giảm 25%");
			mail.setSendTo(account.getEmail());
			String message = this.mailService.sendMail(model, mail);
			return ResponseEntity.status(HttpStatus.CREATED).body(new String[] {message});
		} catch (MessagingException ex) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(new String[] {ex.getMessage()});
		}
	}

	// update account
	@PutMapping("{id}")
	public ResponseEntity<Account> update(@PathVariable Integer id, @RequestBody Account account) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		Account accountAuth = accountService.findByUserNameIgnoreCase(userName);
		if (userName == "anonymousUser") {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		} else if (accountAuth.getId_Account() != account.getId_Account()) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		} else {
			try {
				String passPE = passwordEncoder.encode(account.getPassword());
				account.setPassword(passPE);
				accountService.create(account);
				return new ResponseEntity<>(account, HttpStatus.CREATED);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		}
	}

	// forgot password
	@PostMapping(value = "/forgot-password")
	public ResponseEntity ForgotPassword(Model model, @RequestParam String email) {
		Account account = accountService.findByEmailIgnoreCase(email);
		int otp = otpService.generateOTP(account.getUser_Name());
		String otp1 = String.valueOf(otp);

		
		if (account != null) {
			
			/*
			 * String pass = CreateUtils.createCode(); String newPass =
			 * passwordEncoder.encode(pass);
			 * 
			 * account.setPassword(newPass); accountService.create(account);
			 */
			
			// gui mail
			try {

				model.addAttribute("username", account.getUser_Name());
				model.addAttribute("password", otp1);

				MailDTO mail = new MailDTO();
				mail.setTitle("[Hương Quê] OTP xác nhận.");
				mail.setSendTo(account.getEmail());
				mail.setEmailTemplate(EmailTemplate.FORGET_PASSWORD_OTP);

				String message = this.mailService.sendMail(model, mail);

				return ResponseEntity.status(HttpStatus.OK).body(new String[] {message});
			} catch (MessagingException e) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body(new String[] {e.getMessage()});
			}
		} else {
			model.addAttribute("message", "Vui lòng kiểm tra lại địa chỉ email.");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/forgot-password/otp")
	public ResponseEntity OTP(@RequestParam Integer otp,Model model) {
		
		if(otp >= 0){
			String username = otpService.getUsernameOtp(otp);
			int serverOtp = otpService.getOtp(username);
			
			if(serverOtp > 0){
				if(otp == serverOtp){
					
					Account account = accountService.findByUserNameIgnoreCase(username);
					if (account != null) {
						String pass = CreateUtils.createCode();
						String newPass = passwordEncoder.encode(pass);

						account.setPassword(newPass);
						accountService.create(account);
						otpService.clearOTP(username);
						otpService.clearUsernameOTP(otp);
						// gui mail
						try {

							model.addAttribute("username", account.getUser_Name());
							model.addAttribute("password", pass);

							MailDTO mail = new MailDTO();
							mail.setTitle("[Hương Quê] Mật Khẩu Mới Của Bạn");
			mail.setSendTo(account.getEmail());
							mail.setEmailTemplate(EmailTemplate.FORGET_PASSWORD);

							String message = this.mailService.sendMail(model, mail);

							return ResponseEntity.status(HttpStatus.OK).body(new String[] {message});
						} catch (MessagingException e) {
							return ResponseEntity.status(HttpStatus.CONFLICT).body(new String[] {e.getMessage()});
						}
					} else {
						model.addAttribute("message", "Vui lòng kiểm tra lại địa chỉ email.");
						return new ResponseEntity<>(HttpStatus.NOT_FOUND);
					}
						
				}else{
					return ResponseEntity.status(HttpStatus.CONFLICT).body(new String[] {"Lỗi 1"});	
				}
			}else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new String[] {"Lỗi 2"});				
			}
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new String[] {"Lỗi 3"});			
		}
			
		
	}

}
