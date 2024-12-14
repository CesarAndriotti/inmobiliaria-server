package com.inmobiliaria.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor  
@NoArgsConstructor
public class ResponseAgentUserDto {
    
    private int agentId;
    private int userId;
    private String message;
}
