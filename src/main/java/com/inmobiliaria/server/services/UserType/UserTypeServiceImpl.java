package com.inmobiliaria.server.services.UserType;

import java.util.List;
import org.hibernate.PessimisticLockException;
import org.hibernate.exception.SQLGrammarException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import com.inmobiliaria.server.models.UserType;
import com.inmobiliaria.server.repositories.UserType.UserTypeRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.OptimisticLockException;
import jakarta.persistence.QueryTimeoutException;

@Service
public class UserTypeServiceImpl implements UserTypeService{

    @Autowired
    UserTypeRepository userTypeRepository;

    public List<UserType> showUserTypeList() {
        
        try {
            List<UserType> userTypeListed = userTypeRepository.findAll();
            return userTypeListed;

        } catch (QueryTimeoutException e) {
            throw new IllegalStateException("La consulta excedió el tiempo límite permitido.", e);
        } catch (SQLGrammarException e) {
            throw new IllegalStateException("Error de sintaxis SQL en la consulta.", e);
        } catch (DataAccessResourceFailureException e) {
            throw new IllegalStateException("La base de datos está inaccesible.", e);
        } catch (Exception e) {
            throw new RuntimeException("Ocurrió un error inesperado al realizar la consulta.", e);
        }
    
    }
    
    @Override
    public UserType registerUserType(UserType userType) {
        
        try {
            UserType newUserType = userTypeRepository.save(userType);
            return newUserType;

        } catch (DataIntegrityViolationException e) {
            throw new IllegalStateException("Violación de integridad: No se pueden insertar datos duplicados o inválidos.", e);
        } catch (SQLGrammarException e) {
            throw new IllegalStateException("Error de sintaxis SQL en la inserción.", e);
        } catch (DataAccessResourceFailureException e) {
            throw new IllegalStateException("La base de datos está inaccesible.", e);
        } catch (Exception e) {
            throw new RuntimeException("Ocurrió un error inesperado al insertar la entidad.", e);
        }
    
    }

    @Override
    public UserType updateUserType(UserType userType) {
        
        try {
            UserType userTypeUpdated = userTypeRepository.save(userType); 
            return userTypeUpdated;
            
        } catch (DataIntegrityViolationException e) {
            throw new IllegalStateException("Violación de integridad al actualizar la entidad.", e);
        } catch (SQLGrammarException e) {
            throw new IllegalStateException("Error de sintaxis SQL en la actualización.", e);
        } catch (OptimisticLockException e) {
            throw new IllegalStateException("Conflicto de concurrencia durante la actualización.", e);
        } catch (DataAccessResourceFailureException e) {
            throw new IllegalStateException("La base de datos está inaccesible.", e);
        } catch (Exception e) {
            throw new RuntimeException("Ocurrió un error inesperado al actualizar la entidad.", e);
        }
    }

    @Override
    public Integer deleteUserType(Integer id) {
         
        try {
            userTypeRepository.deleteById(id);
            return id;
        } catch (EmptyResultDataAccessException e) {
        throw new EntityNotFoundException("No se encontró la entidad con ID: " + id);
        } catch (SQLGrammarException e) {
            throw new IllegalStateException("Error de sintaxis SQL al eliminar la entidad.", e);
        } catch (PessimisticLockException e) {
            throw new IllegalStateException("Conflicto de bloqueo al intentar eliminar la entidad.", e);
        } catch (DataAccessResourceFailureException e) {
            throw new IllegalStateException("La base de datos está inaccesible.", e);
        } catch (Exception e) {
            throw new RuntimeException("Ocurrió un error inesperado al eliminar la entidad.", e);
        }
    }

    
}
