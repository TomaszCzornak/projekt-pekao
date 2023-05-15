package com.pekao.projektpekao.repository;

import com.pekao.projektpekao.domain.Author.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;
@RepositoryRestResource
public interface AuthorRepository extends JpaRepository<Author, Long> {

    Optional<Author> findByFirstName(String name);
    Author findAuthorByLastName(String lastName);
    Optional<Author> findById(Long id);

}

