package com.fpoly.huongque.duantotnghiep.controller;

import com.fpoly.huongque.duantotnghiep.entity.Account;
import com.fpoly.huongque.duantotnghiep.service.AccountService;
import com.fpoly.huongque.duantotnghiep.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/security")
public class AccountSecurityController {
	@Autowired
	UserService Userservice;
	@Autowired
	AccountService accountService;

	// login
	@RequestMapping("/login/success")
	public ResponseEntity<Account> succes() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		Account account = accountService.findByUserNameIgnoreCase(userName);
		if (account.is_Enabled() == false) {
			return new ResponseEntity<>(HttpStatus.CHECKPOINT);
		}
		return new ResponseEntity<>(account, HttpStatus.OK);
	}

	@RequestMapping("/login/error")
	public ResponseEntity loginError(Model model) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
	}

	// log-out
	@GetMapping(value = "/log-out/success")
	public HttpStatus logoff(Model model) throws Exception {
		return HttpStatus.OK;

	}

	// failed=403
	@GetMapping(value = "/403")
	public HttpStatus failed403(Model model) throws Exception {
		return HttpStatus.FORBIDDEN;
	}

	// auth 2

	@GetMapping("/oauth2/login/success")
	public HttpStatus success(OAuth2AuthenticationToken oauth2) {
		Userservice.loginFormOAuth2(oauth2);
		System.out.println("alo : " + oauth2);
		String email = oauth2.getPrincipal().getAttribute("email");
		Account existingUsersEmail = accountService.findByEmailIgnoreCase(email);
		if (existingUsersEmail != null) {
			return HttpStatus.OK;
		}
		String fullname = oauth2.getPrincipal().getAttribute("name");
		String username = oauth2.getPrincipal().getAttribute("sub");
		Account account = new Account();
		account.setUser_Name(username);
		account.setEmail(email);
		account.setPassword(username);
		account.setFull_Name(fullname);
		account.setPoints(0);
		account.set_Enabled(true);
		accountService.create(account);
		return HttpStatus.OK;
	}

	@GetMapping("/oauth2/login/error")
	public HttpStatus auth2Error() {
		return HttpStatus.UNAUTHORIZED;
	}

	// failed=404
//	@GetMapping(value = "/404")
//	public HttpStatus failed404(Model model) throws Exception {
//		model.addAttribute("message", "Ban Khong Co Quyen Truy Xuat");
//		return HttpStatus.NOT_FOUND;
//	}
}
