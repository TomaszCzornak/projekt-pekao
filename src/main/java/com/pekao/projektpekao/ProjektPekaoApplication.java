package com.pekao.projektpekao;

import com.pekao.projektpekao.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjektPekaoApplication implements CommandLineRunner {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ElectronicJournalRepository electronicJournalRepository;

    public ProjektPekaoApplication(BookRepository bookRepository, AuthorRepository authorRepository, CommentRepository commentRepository, UserRepository userRepository, ElectronicJournalRepository electronicJournalRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.electronicJournalRepository = electronicJournalRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(ProjektPekaoApplication.class, args);
    }


    @Override
    public void run(String... args) {
//        Optional<Author> authorCheck = authorRepository.findById(1L);
//        Optional<Comment> commentCheck = commentRepository.findById(1L);
//        Optional<User> userCheck = userRepository.findById(1L);
//        Optional<Book> bookCheck = bookRepository.findById(1L);
//        if (authorCheck.isEmpty() || commentCheck.isEmpty() || userCheck.isEmpty() || bookCheck.isEmpty()) {
//            Author author2 = new Author("Autor_Mock", "Nazwisko_Mock");
//            Author author1 = new Author("Mock_Autor", "Mock_Nazwisko");
//            authorRepository.saveAll(List.of(author1, author2));
//            Comment comment1 = commentRepository.save(new Comment("Dupa Jasiu"));
//            Comment comment2 = commentRepository.save(new Comment("Kolejny komentarz"));
//            Comment comment3 = commentRepository.save(new Comment("Ta książka jest ok"));
//            User user1 = new User("Tomek", "Czornak", "tomek@gmail.com", new SimpleDateFormat("dd-MM-yyyy HH:mm:ss z")
//                    .format(new Date()), List.of(comment3));
//            User user2 = new User("Marek", "Nowakowski", "mareknowakowski@gmail.com", new SimpleDateFormat("dd-MM-yyyy HH:mm:ss z")
//                    .format(new Date()), List.of(comment1, comment2));
//            userRepository.saveAll(List.of(user1, user2));
//            commentRepository.saveAll(List.of(comment1, comment2, comment3));
//
//            Book book1 = new Book("Spring w Akcji", author1, List.of(comment2), Book.Publisher.ZNAK);
//            Book book2 = new Book("Java", author2, List.of(comment1), Book.Publisher.PWN);
//            Book book3 = new Book("Programowanie funkcyjne", author2, List.of(comment3), Book.Publisher.AGORA);
//            bookRepository.saveAll(List.of(book1, book2, book3));
        }
    }

