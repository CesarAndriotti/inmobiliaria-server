package com.inmobiliaria.server.dto.Session;

import java.time.LocalDateTime;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SessionRequest {

    @NotNull
    private LocalDateTime start_datetime;

    @NotNull
    private LocalDateTime finish_datetime;

    @NotNull
    private Integer userId;

    @NotNull
    private Integer sessionStateId;
}
