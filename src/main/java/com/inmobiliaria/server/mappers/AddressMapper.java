package com.inmobiliaria.server.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.inmobiliaria.server.dto.Address.AddressDataDto;
import com.inmobiliaria.server.dto.Address.AddressRequest;
import com.inmobiliaria.server.dto.Address.AddressResponse;
import com.inmobiliaria.server.models.Address;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "city", ignore = true)
    Address requestToEntity(AddressRequest addressRequest);
    AddressResponse entityToResponse(Address address);
    Address addressDataToEntity(AddressDataDto addressDataDto);

    AddressDataDto entityToAddressData(Address address);
    List<Address> toEntityList(List<AddressRequest> addressRequestList);

    List<AddressResponse> toDtoList(List<Address> addressList);
}
