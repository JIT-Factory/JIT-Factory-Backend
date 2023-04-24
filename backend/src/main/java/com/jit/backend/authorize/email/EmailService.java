package com.jit.backend.authorize.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import org.thymeleaf.spring6.SpringTemplateEngine;


import java.io.UnsupportedEncodingException;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender emailSender;
    private final SpringTemplateEngine templateEngine;
    private String authNum;

    //랜덤 인증 코드
    public void createCode() {
        Random random = new Random();
        StringBuffer key = new StringBuffer();
        for(int i=0;i<8;i++) {
            int index = random.nextInt(3);
            switch (index) {
                case 0 :
                    key.append((char) ((int)random.nextInt(26) + 97));
                    break;
                case 1:
                    key.append((char) ((int)random.nextInt(26) + 65));
                    break;
                case 2:
                    key.append(random.nextInt(9));
                    break;
            }
        }
        authNum = key.toString();
    }

    //메일양식
    public MimeMessage createEmailForm(String email) throws MessagingException, UnsupportedEncodingException {
        createCode();
        MimeMessage message = emailSender.createMimeMessage();
        message.addRecipients(MimeMessage.RecipientType.TO, email);
        message.setSubject("JIT Factory 회원가입 인증 번호");
        message.setFrom("sbs-dev@naver.com");
        message.setText(setContext(authNum), "utf-8", "html");

        return message;
    }

    //메일전송
    public String sendEmail(String toEmail) throws MessagingException, UnsupportedEncodingException {
        //전송할 메일 정보 설정
        MimeMessage emailForm = createEmailForm(toEmail);
        emailSender.send(emailForm);
        return authNum;
    }

    //메일내용
    public String setContext(String code) {
        Context context = new Context();
        context.setVariable("code", code);
        return templateEngine.process("mail", context); //mail.html
    }
}
