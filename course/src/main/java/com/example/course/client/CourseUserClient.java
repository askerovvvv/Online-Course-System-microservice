package com.example.course.client;

import com.example.course.models.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "example", url = "http://localhost:8090/api/v1/user")
public interface CourseUserClient {

    @GetMapping("/get-user/{id}")
    UserDto getUserById(@PathVariable("id") Long id);
}
