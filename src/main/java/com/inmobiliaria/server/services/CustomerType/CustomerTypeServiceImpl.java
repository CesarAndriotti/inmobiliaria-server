package com.inmobiliaria.server.services.CustomerType;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.inmobiliaria.server.dto.CustomerType.CustomerTypeResponse;
import com.inmobiliaria.server.exceptions.CustomException;
import com.inmobiliaria.server.mappers.CustomerTypeMapper;
import com.inmobiliaria.server.repositories.CustomerType.CustomerTypeRepository;
import com.inmobiliaria.server.models.CustomerType;

@Service
public class CustomerTypeServiceImpl implements CustomerTypeService {

    @Autowired
    CustomerTypeRepository customerTypeRepository;
    @Autowired
    Environment env;
    @Autowired
    CustomerTypeMapper customerTypeMapper;

    @Override
    public List<CustomerTypeResponse> getAllCustomerTypes() throws CustomException {

        try {

            List<CustomerTypeResponse> customerTypeList = customerTypeMapper.toDtoList(customerTypeRepository.findAll());
            return customerTypeList;
        } catch (DataAccessException e) {

            throw new CustomException(
                    env.getProperty("data.access-error") + ": " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            throw new CustomException(
                    env.getProperty("unhandled-exception") + ": " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
