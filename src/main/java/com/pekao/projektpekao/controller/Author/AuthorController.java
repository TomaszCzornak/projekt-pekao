package com.pekao.projektpekao.controller.Author;

import com.pekao.projektpekao.domain.Author.Author;
import com.pekao.projektpekao.domain.Author.AuthorParams;
import com.pekao.projektpekao.domain.Author.AuthorParamsMapper;
import com.pekao.projektpekao.service.AuthorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/author")
public class AuthorController {
    
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/all")
    public AuthorsResponse getAllAuthors() {
        List<AuthorDto> authorDtoList = AuthorDtoMapper.toAuthorDtoList(authorService.findAllAuthors());
        return AuthorsResponse.builder()
                .authorResponseList(authorDtoList)
                .build();
    }

    @GetMapping("/{id}")
    public AuthorResponse getAuthorById(@PathVariable("id") Long id) {
        AuthorWithBooksDto singleAuthor = AuthorWithBooksDtoMapper.toAuthorWithBooksDto(authorService.findAuthorById(id));
        return AuthorResponse.builder()
                .authorResponse(singleAuthor)
                .build();
    }

    @DeleteMapping("/{id}")
    public void deleteAuthorById(@PathVariable("id") Long id) {
        authorService.removeAuthorById(id);
    }

    @PostMapping()
    public AuthorOnlyResponse postAuthor(@RequestBody AuthorDto authorDto) {
        AuthorParams authorParams = AuthorParamsMapper.toAuthorParams(authorDto);
        Author authorSaved = authorService.addAuthor(authorParams);
        AuthorDto authorDto1 = AuthorDtoMapper.fromParamsToAuthorDto(authorSaved);
        return AuthorOnlyResponse.builder()
                .authorOnlyResponse(authorDto1)
                .build();
    }

    @PutMapping("/{id}")
    public void putAuthor(@PathVariable("id") Long id, @RequestBody AuthorDto authorDto) {
        if (!id.equals(authorDto.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "id does not match");
        }
        AuthorParams authorParams = AuthorParamsMapper.toAuthorParams(authorDto);
        authorService.addAuthor(authorParams);
    }
}
