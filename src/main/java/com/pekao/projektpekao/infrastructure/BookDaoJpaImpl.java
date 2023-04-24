package com.pekao.projektpekao.infrastructure;

import com.pekao.projektpekao.entity.Book;
import com.pekao.projektpekao.repository.BookRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("BookDaoJpaImpl")
public class BookDaoJpaImpl implements BookDao {

    private final BookRepository bookRepository;

    public BookDaoJpaImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> findAllBooks() {
        return bookRepository.findAllBooks();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.getBookById(id);
    }

    @Override
    public Optional<Book> findByTitle(String title) {
        return bookRepository.getBookByTitle(title);
    }


    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);

    }

    @Override
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }


    @Override
    public Book updateBook(Book book) {
        return bookRepository.save(book);
    }
}
