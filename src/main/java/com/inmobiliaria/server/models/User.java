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
@Table(name = "user",

                indexes = {
                                @Index(name = "fk_user_agent1_idx", columnList = "agent_id"),
                                @Index(name = "fk_user_user_type1_idx", columnList = "user_type_id"),
                })
public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(nullable = false)
        private Integer id;

        @Column(nullable = false, length = 60)
        private String nick;

        @Column(nullable = false, length = 500)
        private String password;

        @OneToOne
        @JoinColumn(name = "agent_id", nullable = false, foreignKey = @ForeignKey(name = "fk_user_agent1"))
        private Agent agent;

        @ManyToOne
        @JoinColumn(name = "user_type_id", nullable = false, foreignKey = @ForeignKey(name = "fk_user_user_type1"))
        private UserType userType;
}
