package com.pekao.projektpekao;

import com.pekao.projektpekao.domain.Author.Author;
import com.pekao.projektpekao.domain.Author.AuthorParams;
import com.pekao.projektpekao.domain.Comment.Comment;
import com.pekao.projektpekao.domain.Comment.CommentParams;
import com.pekao.projektpekao.domain.User.User;
import com.pekao.projektpekao.domain.User.UserParams;
import com.pekao.projektpekao.domain.Book.Book;
import com.pekao.projektpekao.domain.Book.BookParams;

import java.time.LocalDate;
import java.util.List;

public class BookTestUtility {

    public static BookParams createBookParamsWithPublisher(Book.Publisher publisher) {
        return BookParams.builder()
                .title("Spring w Akcji")
                .publisher(publisher)
                .build();
    }

    public static Book createBookWithPublisher(Book.Publisher publisher) {
        return Book.builder()
                .title("Spring w Akcji")
                .publisher(publisher)
                .build();
    }
    public static List<Book> createBookList() {
        return List.of(
                Book.builder()
                        .title("Spring w Akcji")
                        .publisher(Book.Publisher.ZNAK)
                        .build(),
                Book.builder()
                        .title("Spring w Akcji 2")
                        .publisher(Book.Publisher.AGORA)
                        .build(),
                Book.builder()
                        .title("Spring w Akcji 3")
                        .publisher(Book.Publisher.PWN)
                        .build()
        );
    }

    public static BookParams createBookWithTitleAndAuthor(String title, Author author) {
        return BookParams.builder()
                .title(title)
                .authorParams(AuthorParams.builder()
                        .id(author.getId())
                        .firstName(author.getFirstName())
                        .lastName(author.getLastName())
                        .build())
                .publisher(Book.Publisher.AGORA)
                .build();
    }



    public static BookParams createBookWithTitleAuthorAndPublisherAndCommentsAndUser(String title, Book.Publisher publisher, AuthorParams authorParams) {
        return BookParams.builder()
                .title(title)
                .publisher(publisher)
                .authorParams(AuthorParams.builder()
                        .id(authorParams.getId())
                        .firstName("Mary")
                        .lastName("Jane")
                        .build())
                .commentParamsList((List.of(
                                        CommentParams.builder()
                                                .content("Testowy Komentarz 2")
                                                .userParams(UserParams.builder()
                                                        .firstName("Użytkownik")
                                                        .lastName("Testowy")
                                                        .email("testowy@użytkownik.pl")
                                                        .createdAt(LocalDate.now())
                                                        .build())

                                                .build())))
                        .build();


    }

    public static Book createBookAllParameters(Author author) {
        return Book.builder()
                .title("Książka Testowa")
                .author(Author.builder()
                        .withId(author.getId())
                        .withFirstName(author.getFirstName())
                        .withLastName(author.getLastName())
                        .build())
                .publisher(Book.Publisher.AGORA)
                .commentList((List.of(
                        Comment.builder()
                                .content("Testowy Komentarz 2")
                                .user(User.builder()
                                        .firstName("Użytkownik")
                                        .lastName("Testowy")
                                        .email("testowy@użytkownik.pl")
                                        .createdAt(LocalDate.now())
                                        .buildNewEntity())
                                .buildNewEntity())))
                .buildNewEntity();


    }

}
