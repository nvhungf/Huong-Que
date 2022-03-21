package com.fpoly.huongque.duantotnghiep.dto;

import com.fpoly.huongque.duantotnghiep.constant.EmailTemplate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailDTO {
	
	private String sendTo;
	private String sendFrom = "Huong Que <suport@huongque.vn>";
	private String title;
	private String message;
	private EmailTemplate emailTemplate = EmailTemplate.VERIFY_ACCOUNT_TEMPLATE;
	private boolean status;

}
