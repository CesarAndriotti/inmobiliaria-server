package com.inmobiliaria.server.repositories.Owner;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.inmobiliaria.server.models.Owner;

@Repository
public interface OwnerRepository extends CrudRepository<Owner, Integer>{

    public <S extends Owner> S save(S entity);
    public Optional<Owner> findByCustomerIdAndPropertyId(Integer customerId, Integer propertyId);
}
