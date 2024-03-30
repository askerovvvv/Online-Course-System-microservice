package com.example.course.controller;

import com.example.course.models.responsesDto.SectionDto;
import com.example.course.service.SectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/section")
public class SectionController {

    private final SectionService sectionService;

    // TODO: filter sections, categories, courses using param
    @GetMapping("/get/sections")
    public ResponseEntity<?> findAllSections() {
        return ResponseEntity.status(HttpStatus.OK).body(sectionService.findAllSections());
    }

    @PostMapping("/add/section")
    public ResponseEntity<?> addSections(@RequestBody SectionDto sectionData) {
        sectionService.addSection(sectionData);
        return ResponseEntity.status(HttpStatus.CREATED).body("Section added");
    }

}
