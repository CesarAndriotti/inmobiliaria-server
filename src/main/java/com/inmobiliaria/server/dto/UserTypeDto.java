package com.inmobiliaria.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor  
@NoArgsConstructor 
public class UserTypeDto {

    private Integer id;
    private String type;
}

