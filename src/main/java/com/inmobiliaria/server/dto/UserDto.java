package com.inmobiliaria.server.dto;

import com.inmobiliaria.server.models.Agent;
import com.inmobiliaria.server.models.UserType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor  
@NoArgsConstructor 
public class UserDto {

    private int id;
    private String nick;
    private String password;
    private Agent agent;
    private UserType user_type;
}
