package com.inmobiliaria.server.dto.Property;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertyResponse {

    @NotNull
    private Integer id;
    
    @NotNull
    private Float total_area;

    @NotNull
    private Boolean water;

    @NotNull
    private Boolean electricity;

    @NotNull
    private Boolean gas;

    @NotNull
    private Boolean sewer;

    @NotNull
    private Boolean asphalt;

    @NotNull
    private Boolean summary;

    // FK
    @NotNull
    private Integer propertyStateId;

    @NotNull
    private Integer propertyTypeId;

    @NotNull
    private Integer addressId;

    @NotNull
    private Integer agentId;
}

