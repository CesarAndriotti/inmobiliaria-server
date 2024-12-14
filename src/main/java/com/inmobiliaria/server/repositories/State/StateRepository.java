package com.inmobiliaria.server.repositories.State;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import com.inmobiliaria.server.models.State;

public interface StateRepository extends CrudRepository<State, Integer> {

    List <State> findAll();
    Optional<State> findById(Integer id);
}
