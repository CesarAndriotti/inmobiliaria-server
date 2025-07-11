package com.inmobiliaria.server.repositories.UserType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.inmobiliaria.server.models.UserType;
import java.util.List;
import java.util.Optional;


@Repository
public interface UserTypeRepository extends JpaRepository <UserType, Integer> {

    public List<UserType> findAll();
    public Optional<UserType> findById(Integer id);
    public Optional<UserType> findByType(String type);
    public <S extends UserType> S save(S userType);
    public void deleteById(Integer id);
}
