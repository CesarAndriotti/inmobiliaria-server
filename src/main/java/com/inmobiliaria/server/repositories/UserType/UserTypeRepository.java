package com.inmobiliaria.server.repositories.UserType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.inmobiliaria.server.models.UserType;
import java.util.List;
import java.util.Optional;


@Repository
public interface UserTypeRepository extends JpaRepository <UserType, Integer> {

    List<UserType> findAll();
    Optional<UserType> findById(Integer id);
    <S extends UserType> S save(S userType);
    void delete(UserType entity);
}
