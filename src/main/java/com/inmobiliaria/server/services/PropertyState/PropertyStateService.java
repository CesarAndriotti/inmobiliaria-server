package com.inmobiliaria.server.services.PropertyState;

import java.util.List;
import com.inmobiliaria.server.exceptions.CustomException;
import com.inmobiliaria.server.models.PropertyState;

public interface PropertyStateService {

    public PropertyState registerPropertyState(PropertyState PropertyState) throws CustomException;
    public List<PropertyState> searchPropertyStates();
    public PropertyState updatePropertyState(PropertyState PropertyState) throws CustomException;
    public PropertyState deletePropertyState(PropertyState PropertyState) throws CustomException;
}
