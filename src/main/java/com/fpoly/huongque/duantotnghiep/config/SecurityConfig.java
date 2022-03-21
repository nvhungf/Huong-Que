package com.fpoly.huongque.duantotnghiep.config;

import com.fpoly.huongque.duantotnghiep.constant.Constant;
import com.fpoly.huongque.duantotnghiep.entity.Account;
import com.fpoly.huongque.duantotnghiep.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.sql.DataSource;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	// @Autowired
	// BCryptPasswordEncoder pe;
	@Autowired
	DataSource dataSource;
	@Autowired
	AccountService AccountService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(username -> {
			try {
				Account account = AccountService.findByUserNameIgnoreCase(username);
				String password = account.getPassword();
				String[] roles = account.getAuthority().stream().map(er -> er.getRole().getId_Role())
						.collect(Collectors.toList()).toArray(new String[0]);
				return User.withUsername(username).password(password).roles(roles).build();
			} catch (NoSuchElementException e) {
				throw new UsernameNotFoundException(username + "not found !!");
			}
		});
	}

	// phân quyền sử dụng và hình thức login
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.csrf().disable()
				.cors().and()
				.httpBasic()
				.and()
				.authorizeRequests()
				.antMatchers("rest/**").hasAnyRole(Constant.Role.ALL)
				.antMatchers("/admin/**").permitAll()
				.antMatchers("login", "/logout").permitAll()
				.anyRequest().permitAll()
				.and().exceptionHandling().accessDeniedPage("/security/403")
				.and().logout().invalidateHttpSession(true)
				.clearAuthentication(true).logoutUrl("/security/log-out")
				.logoutSuccessUrl("/security/log-out/success")
				.and().oauth2Login().loginPage("/auth/login/form")
				.defaultSuccessUrl("/security/oauth2/login/success", true)
				.failureUrl("/security/oauth2/login/error").authorizationEndpoint().baseUri("/oauth2/authorization");
	}

	@Bean(name = "passwordEncoder")
	public BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web
			.ignoring().antMatchers(HttpMethod.OPTIONS, "/**")
			.and()
			.ignoring().antMatchers(HttpMethod.GET, "/swagger-ui/");
	}

}
