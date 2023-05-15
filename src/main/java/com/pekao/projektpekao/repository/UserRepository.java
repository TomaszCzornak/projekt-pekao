package com.pekao.projektpekao.repository;

import com.pekao.projektpekao.domain.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
@RepositoryRestResource
public interface UserRepository extends JpaRepository<User, Long> {

     User findUserByEmail(String email);

}
