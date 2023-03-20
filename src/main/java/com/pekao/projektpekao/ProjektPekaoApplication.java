package com.pekao.projektpekao;

import com.pekao.projektpekao.entity.Comment;
import com.pekao.projektpekao.entity.Book;
import com.pekao.projektpekao.repository.CommentRepository;
import com.pekao.projektpekao.repository.AuthorRepository;
import com.pekao.projektpekao.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.pekao.projektpekao.entity.Author;
import java.util.Arrays;

@SpringBootApplication
public class ProjektPekaoApplication implements CommandLineRunner {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CommentRepository commentRepository;

    public ProjektPekaoApplication(BookRepository bookRepository, AuthorRepository authorRepository, CommentRepository commentRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.commentRepository = commentRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(ProjektPekaoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Author author1 = new Author("Craig", "Walls");
        Author author2 = new Author("Pierre-Yves", "Saumont");

        authorRepository.saveAll(Arrays.asList(author1, author2));

        Comment comment1 = new Comment("Dupa Jasiu");
        Comment comment2 = new Comment("Kolejny komentarz");
        Comment comment3= new Comment("Ta książka jest ok");
        commentRepository.saveAll(Arrays.asList(comment1, comment2, comment3));

        Book book1 = new Book("Spring w Akcji", author1, Arrays.asList(comment1));
        Book book2 = new Book("Java", author2, Arrays.asList(comment2));
        Book book3 = new Book("Programowanie funkcyjne", author2, Arrays.asList(comment3));
        bookRepository.saveAll(Arrays.asList(book1, book2, book3));

    }
}
