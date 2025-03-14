package bondarev.sergey.send.mail.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ErrorMessage(
        HttpStatus status,
        LocalDateTime timestamp,
        String message) {}