package com.example.course.client;

import com.example.course.models.requestsDto.AuthorRequestDto;
import com.example.course.models.responsesDto.AuthorResponseDto;
import jakarta.ws.rs.Path;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "course", url = "http://localhost:8090/api/v1")
public interface CourseTeacherClient {

    @GetMapping("/teacher/get/teacher/by")
    AuthorResponseDto getTeacherById(@RequestParam("teacherId") Long teacherId);

    @GetMapping("/email-sender/send-mail/to/author")
    ResponseEntity<?> sendMailToAuthor(@RequestParam("authorId") Long authorId,  @RequestParam("courseId") Long courseId);


}

