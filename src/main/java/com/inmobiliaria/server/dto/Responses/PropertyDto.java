package com.inmobiliaria.server.dto.Responses;

import org.springframework.web.multipart.MultipartFile;
import com.inmobiliaria.server.models.Appraisal;
import com.inmobiliaria.server.models.Construction;
import com.inmobiliaria.server.models.OperationType;
import com.inmobiliaria.server.models.Price;
import com.inmobiliaria.server.models.Property;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertyDto {

    private Property property; //State, Type, Agent, Address
    private Construction construction;
    private Appraisal appraisal;
    private Price price;
    private OperationType operationType;
    
    //Para la documentacion
    private MultipartFile multipartFile;
}
