package com.inmobiliaria.server.models;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.ForeignKey;

@Entity
@Data
@AllArgsConstructor  
@NoArgsConstructor 
@Table(name = "operation_details",

    indexes = {
        @Index(name = "fk_operation_details_operation1_idx", columnList = "operation_id"),
        @Index(name = "fk_operation_details_property1_idx", columnList = "property_id")
    }
)

public class OperationDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "operation_id", nullable = false, foreignKey = @ForeignKey(name = "fk_operation_operation1"))
    private Operation operation;

    @ManyToOne
    @JoinColumn(name = "property_id", nullable = false, foreignKey = @ForeignKey(name = "fk_operation_details_property1"))
    private Property property;
}
