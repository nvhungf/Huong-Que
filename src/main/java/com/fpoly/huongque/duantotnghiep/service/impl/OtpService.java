package com.fpoly.huongque.duantotnghiep.service.impl;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.google.common.cache.LoadingCache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;

/**
 * @author shrisowdhaman
 * Dec 15, 2017
 */
@Service
public class OtpService {

	//cache based on username and OPT MAX 8 
	 private static final Integer EXPIRE_MINS = 5;
	 
	 private LoadingCache<String, Integer> otpCache;
	 private LoadingCache<Integer, String> userNameCache;
	 
	 public OtpService(){
		 super();
		 otpCache = CacheBuilder.newBuilder().expireAfterWrite(EXPIRE_MINS, TimeUnit.MINUTES).build(new CacheLoader<String, Integer>() {
				      public Integer load(String key) {
				             return 0;
				       }
				   });
		 userNameCache = CacheBuilder.newBuilder().expireAfterWrite(EXPIRE_MINS, TimeUnit.MINUTES).build(new CacheLoader<Integer, String>() {
		      public String load(Integer key) {
		             return "";
		       }
		   });

	 }
	 
	 
	 
	//This method is used to push the opt number against Key. Rewrite the OTP if it exists
	 //Using user id  as key
	 public int generateOTP(String key){
		 
		Random random = new Random();	
		int otp = 100000 + random.nextInt(900000);
		otpCache.put(key, otp);
		userNameCache.put(otp, key);
		return otp;
	 }
		 
	 //This method is used to return the OPT number against Key->Key values is username
	 public int getOtp(String key){		 
		try{
			 return otpCache.get(key); 
		}catch (Exception e){
		 return 0;			 
		}
	 }
	 
	 public String getUsernameOtp(Integer otp){		 
		try{
			 return userNameCache.get(otp); 
		}catch (Exception e){
		 return null;			 
		}
	 }
		 
	//This method is used to clear the OTP catched already
	public void clearOTP(String key){		 
		 otpCache.invalidate(key);
	 }
	public void clearUsernameOTP(Integer key){		 
		 otpCache.invalidate(key);
	 }
}
