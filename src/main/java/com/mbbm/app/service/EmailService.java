package com.mbbm.app.service;

import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

@Service
public class EmailService {

    public void sendNewUserRegistrationEmail(){
        try
        {
            String smtpHostServer = "smtp.example.com";
            String emailID = "email_me@example.com";

            Properties props = System.getProperties();
            props.put("mail.smtp.host", smtpHostServer);

            Session session = Session.getInstance(props, null);
            MimeMessage msg = new MimeMessage(session);

            //set message headers
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(new InternetAddress("no_reply@example.com", "NoReply-JD"));

            msg.setReplyTo(InternetAddress.parse("no_reply@example.com", false));

            msg.setSubject("", "UTF-8");

            msg.setText("", "UTF-8");

            msg.setSentDate(new Date());

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("toEmail", false));
            System.out.println("Message is ready");
            Transport.send(msg);

            System.out.println("EMail Sent Successfully!!");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


}
