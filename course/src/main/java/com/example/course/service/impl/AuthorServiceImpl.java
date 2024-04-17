package com.example.course.service.impl;

import com.example.course.exceptions.NotFoundException;
import com.example.course.mapper.AuthorMapper;
import com.example.course.models.Author;
import com.example.course.models.requestsDto.AuthorRequestDto;
import com.example.course.models.responsesDto.AuthorResponseDto;
import com.example.course.repository.AuthorRepository;
import com.example.course.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    // TODO: логика изменения имени, емайла автора в сервисе user или удаления юзера


    @Override
    public List<AuthorResponseDto> findAllAuthors() {
        return AuthorMapper.INSTANCE.toAuthorResponseDtos(authorRepository.findAll());
    }

    @Override
    public Author addAuthor(AuthorRequestDto authorRequestDto) {
        Author authorFromDto = AuthorMapper.INSTANCE.toAuthor(authorRequestDto);
        authorFromDto.setCreatedAt(LocalDateTime.now());

        return authorRepository.save(authorFromDto);
    }

    @Override
    public Author findAuthorByEmailOrCreate(AuthorResponseDto authorResponseDto) {
        Author authorFromDb = authorRepository.findAuthorByEmail(authorResponseDto.getEmail());

        if (Objects.isNull(authorFromDb)) {
            Author authorToSave = Author.builder()
                    .email(authorResponseDto.getEmail())
                    .lastname(authorResponseDto.getLastname())
                    .firstname(authorResponseDto.getFirstname())
                    .createdAt(LocalDateTime.now())
                    .build();

            return authorRepository.save(authorToSave);
        }

        return authorFromDb;
    }

    @Override
    public Author findAuthorById(Long authorId) {
        return authorRepository.findById(authorId)
                .orElseThrow(() -> new NotFoundException("Author not found!"));
    }

    @Override
    public void deleteAuthorById(Long authorId) {
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new NotFoundException("Author not found!"));

        authorRepository.delete(author);
    }
}
