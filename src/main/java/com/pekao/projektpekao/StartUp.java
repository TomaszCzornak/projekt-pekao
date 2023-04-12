package com.pekao.projektpekao;

import com.pekao.projektpekao.entity.Author;
import com.pekao.projektpekao.entity.Book;
import com.pekao.projektpekao.entity.Comment;
import com.pekao.projektpekao.entity.User;
import com.pekao.projektpekao.repository.*;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Profile("dev")
public class StartUp implements ApplicationListener<ContextRefreshedEvent> {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ElectronicJournalRepository electronicJournalRepository;

    public StartUp(
            BookRepository bookRepository, AuthorRepository authorRepository, CommentRepository commentRepository,
            UserRepository userRepository, ElectronicJournalRepository electronicJournalRepository
    ) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.electronicJournalRepository = electronicJournalRepository;
    }


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Optional<Author> authorCheck = authorRepository.findById(1L);
        Optional<Comment> commentCheck = commentRepository.findById(1L);
        Optional<User> userCheck = userRepository.findById(1L);
        Optional<Book> bookCheck = bookRepository.findById(1L);
        if (authorCheck.isEmpty() || commentCheck.isEmpty() || userCheck.isEmpty() || bookCheck.isEmpty()) {
            Author author2 = new Author("Autor_Mock", "Nazwisko_Mock");
            Author author1 = new Author("Mock_Autor", "Mock_Nazwisko");
            authorRepository.saveAll(List.of(author1, author2));
            Comment comment1 = commentRepository.save(new Comment("Dupa Jasiu"));
            Comment comment2 = commentRepository.save(new Comment("Kolejny komentarz"));
            Comment comment3 = commentRepository.save(new Comment("Ta książka jest ok"));

            final User user1 = User.builder()
                    .firstName("Tomek")
                    .lastName("Czornak")
                    .email("tomek@gmail.com")
                    .commentList(List.of(
                            comment1,
                            comment2,
                            comment3
                    ))
                    .createdAt(LocalDate.now())
                    .buildNewEntity();

            final User user2 = User.builder()
                    .firstName("Marek")
                    .lastName("Nowakowski")
                    .email("mareknowakowski@gmail.com")
                    .commentList(List.of(
                            comment1,
                            comment2,
                            comment3
                    ))
                    .createdAt(LocalDate.now())
                    .buildNewEntity();

            userRepository.saveAll(List.of(user1, user2));

            Book book1 = new Book("Spring w Akcji", author1, List.of(comment2), Book.Publisher.ZNAK);
            Book book2 = new Book("Java", author2, List.of(comment1), Book.Publisher.PWN);
            Book book3 = new Book("Programowanie funkcyjne", author2, List.of(comment3), Book.Publisher.AGORA);
            bookRepository.saveAll(List.of(book1, book2, book3));
        }
    }
}
