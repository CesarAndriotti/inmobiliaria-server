package com.inmobiliaria.server.services.Owner;

import com.inmobiliaria.server.exceptions.CustomException;
import com.inmobiliaria.server.models.Owner;

public interface OwnerService {

    public Owner addLinkAsOwner(Owner owner) throws CustomException;
    public Owner updateOwner(Owner owner) throws CustomException;
}