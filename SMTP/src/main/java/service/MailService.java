
package service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class MailService {

    private static final Logger logger =
            Logger.getLogger(MailService.class.getName());

    private final JavaMailSender javaMailSender;

    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(String to, String subject, String body) {
        try {
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setTo(to);
            mail.setSubject(subject);
            mail.setText(body);

            javaMailSender.send(mail);

            logger.info("Email sent successfully to " + to);

        } catch (Exception e) {
            logger.log(
                    Level.SEVERE,
                    "Exception while sending email to " + to,
                    e
            );

            throw new RuntimeException("Failed to send email", e);
        }
    }
}
