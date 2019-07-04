package br.edu.ufcg.ccc.andersonjoao.projeto.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;

@Component
public class EmailSender {

    @Autowired
    private JavaMailSender mailSender;

    public void sendMail(String detination) {
        try {
            MimeMessage mail = mailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper( mail );
            helper.setTo(detination);
            helper.setSubject( "Bem vindo ao UCDB" );
            helper.setText("<h3>Seja bem vindo a nossa plataforma</h3>" +
                    "<p>Acesse <a href=\"https://ankerd.github.io/psoft-ufcg-2019.1-frontend/\">" +
                    "https://ankerd.github.io/psoft-ufcg-2019.1-frontend/</a> " +
                    "para começar a <strike>falar mal dos profs</strike> " +
                    "comentar as disciplinas com seus coleguinhas. </p>", true);
            mailSender.send(mail);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
