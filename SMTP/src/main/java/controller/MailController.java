
package controller;

import org.springframework.web.bind.annotation.*;
import service.MailService;

@RestController
@RequestMapping("/mail")
public class MailController {

    private final MailService mailService;

    public MailController(MailService mailService) {
        this.mailService = mailService;
    }

    @PostMapping("/send")
    public String sendMail(
            @RequestParam String to,
            @RequestParam String subject,
            @RequestParam String body) {

        mailService.sendEmail(to, subject, body);
        return "Email sent successfully";
    }
}
