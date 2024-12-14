package com.inmobiliaria.server.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor  
@NoArgsConstructor 
@Table(name = "User",

    indexes = {
        @Index(name = "fk_User_Agent1_idx", columnList = "agent_Id"),
        @Index(name = "fk_User_User_Type1_idx", columnList = "user_Type_Id"),
    }
)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;

    @Column(nullable = false, length = 60)
    private String nick;

    @Column(nullable = false, length = 500)
    private String password;

    @OneToOne
    @JoinColumn(name = "agent_Id", nullable = false, foreignKey = @ForeignKey(name = "fk_User_Agent1"))
    private Agent agent;

    @ManyToOne
    @JoinColumn(name = "user_Type_Id", nullable = false, foreignKey = @ForeignKey(name = "fk_User_User_Type1"))
    private UserType user_type;
}
