package com.inmobiliaria.server.dto.Property;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertyRequest {

    @NotNull
    private Float totalArea;

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
    private String summary;

    //FK
    @NotNull
    private Integer propertyStateId;

    @NotNull
    private Integer propertyTypeId;

    @NotNull
    private String addressStreetName;

    @NotNull
    private Integer cityId;

    @NotNull
    private String addressNumber;

    @NotNull
    private Integer agentId;
}
