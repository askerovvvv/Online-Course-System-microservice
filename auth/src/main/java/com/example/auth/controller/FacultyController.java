package com.example.auth.controller;

import com.example.auth.models.requestsDto.FacultyRequestDto;
import com.example.auth.models.responsesDto.FacultyResponseDto;
import com.example.auth.service.FacultyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/faculty")
public class FacultyController {

    private final FacultyService facultyService;

    @GetMapping("/get/faculties")
    public ResponseEntity<List<FacultyResponseDto>> findAllFaculties() {
        return ResponseEntity.status(HttpStatus.OK).body(facultyService.findAllFaculties());
    }

    @PostMapping("/add/faculty")
    public ResponseEntity<String> addFaculty(@RequestBody FacultyRequestDto facultyRequestData) {
        facultyService.addFaculty(facultyRequestData);
        return ResponseEntity.status(HttpStatus.OK).body("Faculty added successfully.");
    }

    @GetMapping("/get/faculty/by/id")
    public ResponseEntity<FacultyResponseDto> findFacultyById(@RequestParam("facultyId") int id) {
        return ResponseEntity.status(HttpStatus.OK).body(facultyService.findFacultyDtoById(id));
    }

    @DeleteMapping("/delete/faculty/by/id")
    public ResponseEntity<String> deleteFacultyById(@RequestParam("facultyId") int id) {
        facultyService.deleteFacultyById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Faculty deleted successfully.");
    }

}
