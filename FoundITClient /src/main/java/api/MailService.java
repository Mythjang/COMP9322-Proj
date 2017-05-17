package api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * Created by liusiying on 2017/5/16.
 */
@Service
public class MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    public void sendMail(String to, String subject, String content) {
        new Thread(new SendEmailRunnable(to, subject, content)).start();
        System.out.println("afafdafda");
    }

    private class SendEmailRunnable implements Runnable{
        private String to;
        private String subject;
        private String content;

        SendEmailRunnable(String to, String subject, String content){
            this.to = to;
            this.subject = subject;
            this.content = content;
        }

        @Override
        public void run() {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(sender);
            message.setTo(to);
            message.setSubject(subject);
            message.setText(content);

            try {
                javaMailSender.send(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
