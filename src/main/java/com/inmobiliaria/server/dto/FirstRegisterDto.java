package com.inmobiliaria.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.inmobiliaria.server.models.*;

@Data
@AllArgsConstructor  
@NoArgsConstructor
public class FirstRegisterDto {

    private Agent agent;
    private User user;
}
