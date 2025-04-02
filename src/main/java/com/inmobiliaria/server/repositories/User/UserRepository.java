package com.inmobiliaria.server.repositories.User;

import org.springframework.data.jpa.repository.JpaRepository;
import com.inmobiliaria.server.models.User;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer>{

    List<User> findAll();
    Optional<User> findById(int id);
    Optional<User> findByNick(String nick);
    User save(User user);
}



