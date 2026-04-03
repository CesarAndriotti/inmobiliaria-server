package com.inmobiliaria.server.repositories.UserType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.inmobiliaria.server.models.UserType;
import java.util.Optional;

@Repository
public interface UserTypeRepository extends JpaRepository<UserType, Integer> {

    Optional<UserType> findByName(String name);
}
