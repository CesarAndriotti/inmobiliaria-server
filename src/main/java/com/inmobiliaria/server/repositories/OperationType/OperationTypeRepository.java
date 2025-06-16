package com.inmobiliaria.server.repositories.OperationType;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.inmobiliaria.server.models.OperationType;

@Repository
public interface OperationTypeRepository extends CrudRepository <OperationType, Integer> {

    public List<OperationType> findAll();
    
    <S extends OperationType> S save(S OperationType);
}
