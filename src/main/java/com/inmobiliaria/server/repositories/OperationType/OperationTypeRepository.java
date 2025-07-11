package com.inmobiliaria.server.repositories.OperationType;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.inmobiliaria.server.models.OperationType;

@Repository
public interface OperationTypeRepository extends CrudRepository <OperationType, Integer> {

    public List<OperationType> findAll();
    public <S extends OperationType> S save(S OperationType);
    public Optional<OperationType> findById(int id);
    public Optional<OperationType> findByType(String type);
    public void deleteById(int id);
}
