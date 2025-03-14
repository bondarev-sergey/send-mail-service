package bondarev.sergey.send.mail.exception;

import bondarev.sergey.send.mail.rest.controller.EmailController;
import jakarta.mail.MessagingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.time.LocalDateTime;

@RestControllerAdvice
public class MyExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(EmailController.class);

    @ExceptionHandler(value = { MailException.class, MessagingException.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorMessage> handleMailException(MailException ex) {
        LOG.error("Error working on email: {}", ex.toString());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorMessage(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        LocalDateTime.now(),
                        ex.toString()));
    }

    @ExceptionHandler(value = IOException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorMessage> handleIOException(MailException ex) {
        LOG.error("Error getting bytes from attachments: {}", ex.toString());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorMessage(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        LocalDateTime.now(),
                        ex.toString()));
    }

}
