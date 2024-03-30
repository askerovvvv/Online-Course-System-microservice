package com.example.course.service.impl;

import com.example.course.exceptions.CustomBadRequestException;
import com.example.course.mapper.AuthorMapper;
import com.example.course.models.Author;
import com.example.course.models.dto.AuthorDto;
import com.example.course.repository.AuthorRepository;
import com.example.course.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    // TODO: логика изменения имени, емайла автора в сервисе user



    @Override
    public Author createAuthor(AuthorDto authorDto) {
        Author authorFromDto = AuthorMapper.INSTANCE.toAuthor(authorDto);
        authorFromDto.setCreatedAt(LocalDateTime.now());

        return authorRepository.save(authorFromDto);
    }

    @Override
    public Author findAuthorByEmailOrCreate(AuthorDto authorDto) {
        Author authorFromDb = authorRepository.findAuthorByEmail(authorDto.getEmail());

        if (Objects.isNull(authorFromDb)) {
            return createAuthor(authorDto);
        }

        return authorFromDb;
    }

    @Override
    public Author findAuthorById(Long authorId) {
        return authorRepository.findById(authorId)
                .orElseThrow(() -> new CustomBadRequestException("Author not found!"));
    }
}
