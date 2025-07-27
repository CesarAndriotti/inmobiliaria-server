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
@Table(name = "Operation",
    
    indexes = {
        @Index(name = "fk_Operation_Operation_Type1_idx" , columnList = "operation_Type_Id"),
        @Index (name = "fk_Operation_Operation_State1_idx" , columnList = "operation_State_Id")
    }
)
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    @Column(nullable = false)
    private String operation_code;

    @Column(nullable = false)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "operation_Type_Id", nullable = false)
    private OperationType operation_type;

    @ManyToOne
    @JoinColumn(name = "operation_State_Id", nullable = false, foreignKey = @ForeignKey(name = "fk_Operation_Operation_State1"))
    private OperationState operation_state;

    @ManyToOne
    @JoinColumn(name = "property_Id", nullable = false, foreignKey = @ForeignKey(name = "fk_Opertation_Property1"))
    private Property property;
}
