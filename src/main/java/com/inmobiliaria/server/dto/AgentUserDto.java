package com.inmobiliaria.server.dto;
import com.inmobiliaria.server.models.Agent;
import com.inmobiliaria.server.models.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor  
@NoArgsConstructor 
public class AgentUserDto {
    
    private Agent agent;
    private User user;
}    