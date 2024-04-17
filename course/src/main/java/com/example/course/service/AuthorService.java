package com.example.course.service;

import com.example.course.models.Author;
import com.example.course.models.requestsDto.AuthorRequestDto;
import com.example.course.models.responsesDto.AuthorResponseDto;

import java.util.List;

public interface AuthorService {
    List<AuthorResponseDto> findAllAuthors();
    Author addAuthor(AuthorRequestDto authorRequestDto);
    Author findAuthorByEmailOrCreate(AuthorResponseDto authorResponseDto);
    Author findAuthorById(Long authorId);
    void deleteAuthorById(Long authorId);
}
