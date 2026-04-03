package com.inmobiliaria.server.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.inmobiliaria.server.dto.Customer.CustomerRequest;
import com.inmobiliaria.server.dto.Customer.CustomerResponse;
import com.inmobiliaria.server.models.Customer;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "customerType", ignore = true)
    @Mapping(target = "address", ignore = true)
    Customer toEntity(CustomerRequest customerRequest);

    @Mapping(source = "customerType.id", target = "customerTypeId")
    @Mapping(source = "address.id", target = "addressId")
    CustomerResponse toDto(Customer customer);

    List<CustomerResponse> toDtoList(List<Customer> customers);
}
