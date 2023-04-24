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

        }
    }

