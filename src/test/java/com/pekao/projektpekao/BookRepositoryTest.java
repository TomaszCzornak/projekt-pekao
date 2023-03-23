//package com.pekao.projektpekao;
//
//import com.pekao.projektpekao.entity.Author;
//import com.pekao.projektpekao.entity.Book;
//import com.pekao.projektpekao.entity.Comment;
//import com.pekao.projektpekao.entity.User;
//import com.pekao.projektpekao.repository.BookRepository;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
//import java.text.SimpleDateFormat;
//import java.util.Arrays;
//import java.util.Date;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//@DataJpaTest
//public class BookRepositoryTest {
//
//    private final BookRepository bookRepository;
//
//    public BookRepositoryTest(BookRepository bookRepository) {
//        this.bookRepository = bookRepository;
//    }
//    @Test
//    void saveBook() {
//        bookRepository.save(new Book("Java", new Author("Mariano", "Italiano"), Arrays.asList(new Comment("Taki tam komentarz", new User("Janusz", "Brajanusz","janusz@polska.com", new SimpleDateFormat("dd-MM-yyyy HH:mm:ss z")
//                .format(new Date()))))));
//        assertThat(bookRepository.findByTitle("Java").isPresent()).isTrue();
//    }
//
//    @Test
//    void deleteBooks() {
//        bookRepository.save(new Book("Java", new Author("Mariano", "Italiano"), Arrays.asList(new Comment("Taki tam komentarz"))));
//        bookRepository.deleteAll();
//        assertThat(bookRepository.count()).isEqualTo(0);
//    }
//}
