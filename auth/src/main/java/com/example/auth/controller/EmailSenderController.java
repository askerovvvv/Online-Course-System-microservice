package com.example.auth.controller;

import com.example.auth.service.EmailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/email-sender")
public class EmailSenderController {

    private final EmailSender emailSender;

    @GetMapping("/send-mail/to/author")
    public ResponseEntity<?> sendMailToTeacher(@RequestParam("authorId") Long authorId, @RequestParam("courseId") Long courseId) {
        emailSender.authorAddedToCourseMessage(authorId, courseId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
