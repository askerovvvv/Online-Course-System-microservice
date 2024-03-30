package com.example.course.service;

import com.example.course.models.Author;
import com.example.course.models.dto.AuthorDto;

public interface AuthorService {

    Author saveAuthor(AuthorDto authorDto);
    Author findAuthorByEmail(AuthorDto authorDto);
}
