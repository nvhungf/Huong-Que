package com.fpoly.huongque.duantotnghiep.service;

import com.fpoly.huongque.duantotnghiep.dto.MailDTO;
import org.springframework.ui.Model;

import javax.mail.MessagingException;

public interface MailService {

    String sendMail(Model model, MailDTO mailDTO) throws MessagingException;
}
