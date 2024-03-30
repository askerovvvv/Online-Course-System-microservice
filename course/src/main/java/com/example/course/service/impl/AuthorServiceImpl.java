package com.example.course.service.impl;

import com.example.course.exceptions.CustomBadRequestException;
import com.example.course.mapper.AuthorMapper;
import com.example.course.models.Author;
import com.example.course.models.dto.AuthorDto;
import com.example.course.repository.AuthorRepository;
import com.example.course.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    // TODO: логика изменения имени, емайла автора в сервисе user



    @Override
    public Author saveAuthor(AuthorDto authorDto) {
        Author authorFromDto = AuthorMapper.INSTANCE.toAuthor(authorDto);
        return authorRepository.save(authorFromDto);
    }

    @Override
    public Author findAuthorByEmail(AuthorDto authorDto) {
        Author authorFromDb = authorRepository.findAuthorByEmail(authorDto.getEmail());

        if (Objects.isNull(authorFromDb)) {
            return saveAuthor(authorDto);
        }

        return authorFromDb;
    }

}
