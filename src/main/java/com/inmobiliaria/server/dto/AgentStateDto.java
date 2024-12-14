package com.inmobiliaria.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor  
@NoArgsConstructor 
public class AgentStateDto {

    private int id;
    private String state;
}
