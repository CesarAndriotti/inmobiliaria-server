package com.inmobiliaria.server.repositories.UserType;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.inmobiliaria.server.models.UserType;
import java.util.List;
import java.util.Optional;


@Repository
public interface UserTypeRepository extends CrudRepository <UserType, Integer> {

    Optional<UserType> findById(Integer id);
    <S extends UserType> S save(S userType);
}
