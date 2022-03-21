package com.fpoly.huongque.duantotnghiep.service.impl;

import com.fpoly.huongque.duantotnghiep.dto.MailDTO;
import com.fpoly.huongque.duantotnghiep.service.MailService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private Configuration config;

    @Autowired
    private JavaMailSender sender;

    @Override
    public String sendMail(Model model, MailDTO mailDTO) throws MessagingException {
        try {
            MimeMessage message = sender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
            Template t = this.config.getTemplate(mailDTO.getEmailTemplate().getTemplate());
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);

            helper.setTo(mailDTO.getSendTo());
            helper.setText(html, true);
            helper.setSubject(mailDTO.getTitle());
            helper.setFrom(mailDTO.getSendFrom());
            this.sender.send(message);
            return "Chúng tôi đã gửi email đến địa chỉ : " + mailDTO.getSendTo() + ", vui lòng kiểm tra hộp thư của bạn.";
        } catch (Exception exception) {
            throw new MessagingException("Lỗi Gửi Mail");
        }
    }
}
