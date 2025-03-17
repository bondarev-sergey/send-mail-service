package bondarev.sergey.send.mail.rest.controller;

import bondarev.sergey.send.mail.service.EmailService;
import jakarta.mail.MessagingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/mail")
public class EmailController {

    private static final Logger LOG = LoggerFactory.getLogger(EmailController.class);

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping
    ResponseEntity<Void> sendMail(
            String from,
            String to,
            String subject,
            String body,
            List<MultipartFile> attachments) throws MessagingException, IOException {
        LOG.info("Sending email to {}", to);

        emailService.send(from, to, subject, body, attachments);
        return ResponseEntity.ok().build();
    }

}
