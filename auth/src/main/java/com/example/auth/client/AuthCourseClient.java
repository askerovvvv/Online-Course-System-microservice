package com.example.auth.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "auth", url = "http://localhost:8020/api/v1/author")
public interface AuthCourseClient {

    @DeleteMapping("/delete/author/by/id")
    ResponseEntity<?> deleteAuthorById(@RequestParam("authorId") Long id);
}
