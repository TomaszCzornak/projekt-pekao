package com.pekao.projektpekao;

import com.pekao.projektpekao.entity.Comment;
import com.pekao.projektpekao.entity.Book;
import com.pekao.projektpekao.entity.User;
import com.pekao.projektpekao.repository.CommentRepository;
import com.pekao.projektpekao.repository.AuthorRepository;
import com.pekao.projektpekao.repository.BookRepository;
import com.pekao.projektpekao.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.pekao.projektpekao.entity.Author;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

@SpringBootApplication
public class ProjektPekaoApplication implements CommandLineRunner {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public ProjektPekaoApplication(BookRepository bookRepository, AuthorRepository authorRepository, CommentRepository commentRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(ProjektPekaoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Author author1 = new Author("Craig", "Walls");
        Author author2 = new Author("Pierre-Yves", "Saumont");

        authorRepository.saveAll(Arrays.asList(author1, author2));

        Book book1 = new Book("Spring w Akcji", author1);
        Book book2 = new Book("Java", author2);
        Book book3 = new Book("Programowanie funkcyjne", author2);
        bookRepository.saveAll(Arrays.asList(book1, book2, book3));
        Comment comment1 = new Comment("Dupa Jasiu", book1);
        Comment comment2 = new Comment("Kolejny komentarz", book2);
        Comment comment3= new Comment("Ta książka jest ok", book3);
        commentRepository.saveAll(Arrays.asList(comment1, comment2, comment3));

        User user1 = new User("Tomek", "Czornak", "tomek@gmail.com", new SimpleDateFormat("dd-MM-yyyy HH:mm:ss z")
                .format(new Date()), Arrays.asList(comment3));
        User user2 = new User("Marek", "Nowakowski", "mareknowakowski@gmail.com", new SimpleDateFormat("dd-MM-yyyy HH:mm:ss z")
                .format(new Date()), Arrays.asList(comment1, comment2));
        userRepository.saveAll(Arrays.asList(user1,user2));


    }
}
