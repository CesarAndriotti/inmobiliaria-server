package com.inmobiliaria.server.models;

import java.sql.Date;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Index;
import jakarta.persistence.ForeignKey;

@Entity
@Data
@AllArgsConstructor  
@NoArgsConstructor 
@Table(name = "operation",
    
    indexes = {
        @Index(name = "fk_operation_operation_type1_idx" , columnList = "operation_type_id"),
        @Index (name = "fk_operation_operation_state1_idx" , columnList = "operation_state_id")
    }
)
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    @Column(nullable = false, length = 60)
    private String operation_code;

    @Column(nullable = false)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "operation_type_id", nullable = false, foreignKey = @ForeignKey(name = "fk_operation_operation_type1"))
    private OperationType operation_type;

    @ManyToOne
    @JoinColumn(name = "operation_state_id", nullable = false, foreignKey = @ForeignKey(name = "fk_operation_operation_state1"))
    private OperationState operation_state;
}
