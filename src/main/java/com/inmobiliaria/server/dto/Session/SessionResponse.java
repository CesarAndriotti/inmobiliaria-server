package com.inmobiliaria.server.dto.Session;

import java.time.LocalDateTime;

import com.inmobiliaria.server.dto.User.UserRequest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SessionResponse {

    @NotNull
    private Integer id;
    
    @NotNull
    private LocalDateTime start_datetime;

    @NotNull
    private LocalDateTime finish_datetime;

    @NotNull
    private Integer userId;

    @NotNull
    private Integer userStateId;

    @NotNull
    private Integer sessionStateId;
}
