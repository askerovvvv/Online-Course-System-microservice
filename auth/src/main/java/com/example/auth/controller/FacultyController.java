package com.example.auth.controller;

import com.example.auth.models.requestsDto.FacultyRequestDto;
import com.example.auth.service.FacultyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/faculty")
public class FacultyController {

    private final FacultyService facultyService;

    @GetMapping("/get/faculties")
    public ResponseEntity<?> findAllFaculties() {
        return ResponseEntity.status(HttpStatus.OK).body(facultyService.findAllFaculties());
    }

    @GetMapping("/get/faculty/by/id")
    public ResponseEntity<?> findFacultyById(@RequestParam("id") int id) {
        return ResponseEntity.status(HttpStatus.OK).body(facultyService.findFacultyById(id));
    }

    @PostMapping("/add/faculty")
    public ResponseEntity<?> addFaculty(@RequestBody FacultyRequestDto facultyRequestData) {
        facultyService.addFaculty(facultyRequestData);
        return ResponseEntity.status(HttpStatus.OK).body("Faculty added successfully.");
    }

    @DeleteMapping("/delete/faculty/by/id")
    public ResponseEntity<?> deleteFacultyById(@RequestParam("id") int id) {
        facultyService.deleteFacultyById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Faculty deleted successfully.");
    }

}
