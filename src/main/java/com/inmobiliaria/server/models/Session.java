package com.inmobiliaria.server.models;

import java.sql.Date;

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
@Table(name = "Session",

    indexes = {
        @Index(name = "fk_Session_User1_idx", columnList = "user_Id"),
        @Index(name = "fk_Session_Session_State1_idx", columnList = "session_State_Id")
    }
)
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    @Column(nullable = false)
    private Date start_datetime;

    @Column(nullable = false)
    private Date finish_datetime;

    @ManyToOne
    @JoinColumn(name = "user_Id", nullable = false, foreignKey = @ForeignKey(name = "fk_Session_User1"))
    private User user;

    @ManyToOne
    @JoinColumn(name = "session_State_Id", nullable = false, foreignKey = @ForeignKey(name = "fk_Session_Session_State1"))
    private SessionState session_state;
}
