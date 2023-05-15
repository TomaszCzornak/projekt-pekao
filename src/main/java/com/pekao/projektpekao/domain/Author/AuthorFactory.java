package com.pekao.projektpekao.domain.Author;

import com.pekao.projektpekao.domain.ElectronicJournal.ElectronicJournal;
import com.pekao.projektpekao.domain.Book.Book;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AuthorFactory {

    public static Author buildNewEntity(CreateAuthorFactoryParams createAuthorFactoryParams) {
        final Author author = new Author();
        author.setId(createAuthorFactoryParams.getId());
        author.setFirstName(createAuthorFactoryParams.getFirstName());
        author.setLastName(createAuthorFactoryParams.getLastName());
        createElectronicJournalEventTypeForAuthor(createAuthorFactoryParams.getFirstName(), createAuthorFactoryParams.getLastName());
        return author;
    }

    public static ElectronicJournal createElectronicJournalEventTypeForAuthor(String firstName, String lastName) {
        return ElectronicJournal.builder()
                .name("Tutaj autorem jest " + firstName + " " + lastName)
                .created(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss z")
                        .format(new Date()))
                .buildNewWithAuthor();
    }

    public static Author buildNewEntity(CreateAuthorFactoryParams createAuthorFactoryParams, Book book) {
        final Author author = new Author();
        author.setId(createAuthorFactoryParams.getId());
        author.setFirstName(createAuthorFactoryParams.getFirstName());
        author.setLastName(createAuthorFactoryParams.getLastName());
        author.setBookList(List.of(book));
        return author;
    }
}

