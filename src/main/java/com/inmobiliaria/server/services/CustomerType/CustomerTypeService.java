package com.inmobiliaria.server.services.CustomerType;

import java.util.List;
import com.inmobiliaria.server.dto.CustomerType.CustomerTypeResponse;
import com.inmobiliaria.server.exceptions.CustomException;

public interface CustomerTypeService {

    List<CustomerTypeResponse> getAllCustomerTypes() throws CustomException;   
}
