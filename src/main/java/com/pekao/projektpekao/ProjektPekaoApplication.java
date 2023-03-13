package com.pekao.projektpekao;

import com.pekao.projektpekao.entity.Author;
import com.pekao.projektpekao.entity.Book;
import com.pekao.projektpekao.repository.AuthorRepository;
import com.pekao.projektpekao.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class ProjektPekaoApplication implements CommandLineRunner {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public ProjektPekaoApplication(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(ProjektPekaoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Author author1 = new Author("John", "Walker");
        Author author2 = new Author("Brian", "May");
        authorRepository.saveAll(Arrays.asList(author1, author2));

        Book book1 = new Book("Spring w Akcji", author1);
        Book book2 = new Book("Java", author2);
        Book book3 = new Book("Programowanie funkcyjne", author2);
        bookRepository.saveAll(Arrays.asList(book1, book2, book3));
    }
}
