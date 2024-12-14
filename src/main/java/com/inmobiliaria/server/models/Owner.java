package com.inmobiliaria.server.models;

import java.sql.Date;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
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
@Table(name = "Owner",

    indexes = {
        @Index(name = "fk_Owner_Property1_idx", columnList = "propertyId"),
        @Index(name = "fk_Owner_Customer1_idx", columnList = "customer_Id")
    }
)
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    @Column(nullable = false)
    private Date since_date;

    @Column(nullable = true)
    private Date to_date;

    @ManyToOne
    @JoinColumn(name = "property_Id", nullable = false, foreignKey = @ForeignKey(name = "fk_Owner_Property1"))
    private Property property;

    @ManyToOne
    @JoinColumn(name = "customer_Id", nullable = false, foreignKey = @ForeignKey(name = "fk_Owner_Customer1"))
    private Customer customer;
}
