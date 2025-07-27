package com.inmobiliaria.server.services.OperationDetails;

import com.inmobiliaria.server.models.OperationDetails;
import com.inmobiliaria.server.exceptions.CustomException;

public interface OperationDetailsService {

    public OperationDetails addLinkAsOperationDetails(OperationDetails operationDetails) throws CustomException;
    public OperationDetails updateOperationDetails(OperationDetails operationDetails) throws CustomException;
}
