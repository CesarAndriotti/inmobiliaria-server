package com.inmobiliaria.server.models;

import java.sql.Date;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor  
@NoArgsConstructor 
@Table(name = "session",

    indexes = {
        @Index(name = "fk_session_user1_idx", columnList = "user_id"),
        @Index(name = "fk_session_session_state1_idx", columnList = "session_state_id")
    }
)
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    @Column(nullable = false)
    private LocalDateTime start_datetime;

    @Column(nullable = true)
    private LocalDateTime finish_datetime;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "fk_session_user1"))
    private User user;

    @ManyToOne
    @JoinColumn(name = "session_state_id", nullable = false, foreignKey = @ForeignKey(name = "fk_session_session_state1"))
    private SessionState sessionState;
}
