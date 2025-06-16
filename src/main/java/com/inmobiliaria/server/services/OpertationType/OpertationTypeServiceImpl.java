package com.inmobiliaria.server.services.OpertationType;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.inmobiliaria.server.exceptions.CustomException;
import com.inmobiliaria.server.models.OperationType;
import com.inmobiliaria.server.repositories.OperationType.OperationTypeRepository;

@Service
public class OpertationTypeServiceImpl implements OpertationTypeService{

    @Autowired
    Environment env;
    @Autowired
    OperationTypeRepository operationTypeRepository;

    @Override
    public List<OperationType> getAllOpertationTypes() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllOpertationTypes'");
    }

    @Override
    public OperationType registerOperationType(OperationType operationType) throws CustomException {
        
        if (operationType == null) {
            
            throw new CustomException(env.getProperty("https.http.client.bad-request"), HttpStatus.BAD_REQUEST);
        }

        OperationType operationTypeRegistered = operationTypeRepository.save(operationType);

        return operationTypeRegistered;
    }

    @Override
    public OperationType updateOperationType(OperationType operationType) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateOperationType'");
    }

    @Override
    public Integer deleteOperationType(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteOperationType'");
    }

}
