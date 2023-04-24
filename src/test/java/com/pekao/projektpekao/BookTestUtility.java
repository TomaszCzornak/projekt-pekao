package com.pekao.projektpekao;

import com.pekao.projektpekao.entity.Author;
import com.pekao.projektpekao.entity.Book;

import java.util.List;

public class BookTestUtility {

    public static Book createBookWithPublisher(Book.Publisher publisher) {
        return Book.builder()
                .title("Spring w Akcji")
                .publisher(publisher)
                .electronicJournal(Book.createElectronicJournalEventType(publisher))
                .buildNewEntity();
    }
    public static Book createBookWithTitleAndAuthor(String title, Author author) {
        return Book.builder()
                .title(title)
                .author(author)
                .publisher(Book.Publisher.AGORA)
                .buildNewEntity();
    }

}
