package com.inmobiliaria.server.repositories.OperationDetails;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inmobiliaria.server.models.OperationDetails;

public interface OperationDetailsRepository extends JpaRepository<OperationDetails, Integer> {

    public <S extends OperationDetails> S save(S entity);
    public Optional<OperationDetails> findByOperationIdAndCustomerId(Integer operationId, Integer customerId);
}
