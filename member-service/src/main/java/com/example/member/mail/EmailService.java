package com.example.member.mail;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;

@Component
public class EmailService {

    private JavaMailSender mailSender;
    private Configuration freemarkerConfig;

    public void send(EmailDTO emailDTO) {

        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("noreply@sollabs.tech");
            messageHelper.setTo(emailDTO.getAddressToSend());
            messageHelper.setSubject("test");
            messageHelper.setText(this.buildEmailTemplate(emailDTO), true);
        };

        try {
            mailSender.send(messagePreparator);
        } catch (MailException e) {
            e.printStackTrace();
        }
    }

    private String buildEmailTemplate(EmailDTO emailDTO) throws IOException, TemplateException {

        Template freemarkerTemplate = freemarkerConfig.getTemplate(this.resolveTemplateType(emailDTO.getEmailType()));

        return FreeMarkerTemplateUtils.processTemplateIntoString(freemarkerTemplate, emailDTO.getVariables());
    }

    private String resolveTemplateType(EmailType emailType) {
        switch (emailType) {
            case VERIFICATION: return "emailVerification.html";
            case TEMP_PASSWORD: return "temporaryPassword.html";
        }

        return null;
    }

    @Autowired
    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Autowired
    public void setFreemarkerConfig(Configuration freemarkerConfig) {
        this.freemarkerConfig = freemarkerConfig;
    }
}
