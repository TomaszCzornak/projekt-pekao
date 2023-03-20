package com.pekao.projektpekao.controller;

import com.pekao.projektpekao.entity.Author;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorController.class);

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }
    @GetMapping("/all")
    public List<Author> getAllAuthors() {
        LOGGER.info("Printing all authors");
        return authorService.findAllAuthors();
    }
    @GetMapping("/{id}")
    public Author getAuthorById(@PathVariable("id") Long id) {
        return authorService.findAuthorById(id);
    }
    @DeleteMapping("/{id}")
    public void deleteAuthorById(@PathVariable("id") Long id) {
        authorService.removeAuthorById(id);
    }
    @PostMapping()
    public Author postAuthor(@RequestBody Author author) {
        return authorService.addAuthor(author);
    }
    @PutMapping("/{id}")
    public void putAuthor(@PathVariable("id") Long id, @RequestBody Author author) {
        if(!id.equals(author.getId())) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "id does not match");
        authorService.updateAuthor(id, author);
    }
}
