package com.example.course.controller;

import com.example.course.models.requestsDto.AuthorRequestDto;
import com.example.course.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/author")
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping("/get/authors")
    public ResponseEntity<?> findAllCategories() {
        return ResponseEntity.status(HttpStatus.OK).body(authorService.findAllAuthors());
    }

    @PostMapping("/add/author")
    public ResponseEntity<?> addAuthor(@RequestBody AuthorRequestDto authorRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authorService.addAuthor(authorRequestDto));
    }

    @GetMapping("/get/author/by/id")
    public ResponseEntity<?> findAuthorById(@RequestParam("authorId") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(authorService.findAuthorById(id));
    }

    @DeleteMapping("/delete/author/by/id")
    public ResponseEntity<?> deleteAuthorById(@RequestParam("authorId") Long id) {
        authorService.deleteAuthorById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Author deleted successfully.");
    } // TODO: update


}
