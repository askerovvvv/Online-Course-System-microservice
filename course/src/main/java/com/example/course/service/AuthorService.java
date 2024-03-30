package com.example.course.service;

import com.example.course.models.Author;
import com.example.course.models.dto.AuthorDto;

public interface AuthorService {

    Author createAuthor(AuthorDto authorDto);
    Author findAuthorByEmailOrCreate(AuthorDto authorDto);
    Author findAuthorById(Long authorId);
}
