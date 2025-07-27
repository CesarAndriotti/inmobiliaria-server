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
@Table(name = "Operation_Details",

    indexes = {
        @Index(name = "fk_Operation_Details_Operation1_idx", columnList = "operation_Id"),
        @Index(name = "fk_Operation_Details_Customer1_idx", columnList = "customer_Id")
    }
)

public class OperationDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "operation_Id", nullable = false, foreignKey = @ForeignKey(name = "fk_Operation_Operation1"))
    private Operation operation;

    @ManyToOne
    @JoinColumn(name = "customer_Id", nullable = false, foreignKey = @ForeignKey(name = "fk_Operation_customer1"))
    private Customer customer;
}
