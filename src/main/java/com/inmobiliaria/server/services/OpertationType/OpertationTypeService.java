package com.inmobiliaria.server.services.OpertationType;

import java.util.List;

import com.inmobiliaria.server.exceptions.CustomException;
import com.inmobiliaria.server.models.OperationType;

public interface OpertationTypeService {

    public List<OperationType> getAllOpertationTypes() throws CustomException;
    public OperationType registerOperationType(OperationType operationType) throws CustomException;
    public OperationType updateOperationType(OperationType operationType) throws CustomException;
    public Integer deleteOperationType(Integer id) throws CustomException;
}
