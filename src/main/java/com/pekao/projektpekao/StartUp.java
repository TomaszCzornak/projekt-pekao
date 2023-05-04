package com.pekao.projektpekao;

import com.pekao.projektpekao.domain.Author;
import com.pekao.projektpekao.domain.book.Book;
import com.pekao.projektpekao.domain.Comment;
import com.pekao.projektpekao.domain.User;
import com.pekao.projektpekao.repository.*;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

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
        boolean authorsAreEmpty = authorRepository.findAll().isEmpty();
        boolean commentsAreEmpty = commentRepository.findAll().isEmpty();
        boolean usersAreEmpty = userRepository.findAll().isEmpty();
        boolean booksAreEmpty = bookRepository.findAll().isEmpty();
        if (authorsAreEmpty || commentsAreEmpty || usersAreEmpty || booksAreEmpty) {
            final Author author1 = Author.builder()
                    .withFirstName("Autor_Mock")
                    .withLastName("Nazwisko_Mock")
                    .buildNewEntity();
            final Author author2 = Author.builder()
                    .withFirstName("Mock_Autor")
                    .withLastName("Mock_Nazwisko")
                    .buildNewEntity();
            authorRepository.saveAll(List.of(author1, author2));
            final Comment comment1 = Comment.builder()
                    .content("Mockowy komentarz 1")
                    .buildNewEntity();
            final Comment comment2 = Comment.builder()
                    .content("Mockowy komentarz 2")
                    .buildNewEntity();
            final Comment comment3 = Comment.builder()
                    .content("Mockowy komentarz 3")
                    .buildNewEntity();

            final User user1 = User.builder()
                    .firstName("Tomek")
                    .lastName("Czornak")
                    .email("tomek@gmail.com")
                    .commentList(List.of(comment1, comment2))
                    .createdAt(LocalDate.now())
                    .buildNewEntity();

            final User user2 = User.builder()
                    .firstName("Marek")
                    .lastName("Nowakowski")
                    .email("mareknowakowski@gmail.com")
                    .commentList(List.of(comment3))
                    .createdAt(LocalDate.now())
                    .buildNewEntity();

            Book bookFromBuilder1 = Book.builder()
                    .title("Spring w Akcji")
                    .author(author1)
                    .commentList(List.of(comment2))
                    .publisher(Book.Publisher.ZNAK)
                    .buildNewEntity();
            Book bookFromBuilder2 = Book.builder()
                    .title("Java")
                    .author(author2)
                    .commentList(List.of(comment1))
                    .publisher(Book.Publisher.PWN)
                    .buildNewEntity();
            Book bookFromBuilder3 = Book.builder()
                    .title("Programowanie funkcyjne")
                    .author(author2)
                    .commentList(List.of(comment3))
                    .publisher(Book.Publisher.AGORA)
                    .buildNewEntity();
            bookRepository.saveAll(List.of(bookFromBuilder1, bookFromBuilder2, bookFromBuilder3));
            userRepository.saveAll(List.of(user1, user2));

        }
    }
}
