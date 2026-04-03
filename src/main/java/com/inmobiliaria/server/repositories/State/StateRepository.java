package com.inmobiliaria.server.repositories.State;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.inmobiliaria.server.models.State;

public interface StateRepository extends JpaRepository<State, Integer> {

    Optional<State> findByName(String name);
    List<State> findByNameContaining(String name);
}
