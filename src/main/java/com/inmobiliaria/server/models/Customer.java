package com.inmobiliaria.server.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;

@Entity
@Data
@AllArgsConstructor  
@NoArgsConstructor 
@Table(name = "customer",
    
    indexes = {
        @Index(name = "fk_customer_customer_type1_idx", columnList = "customer_type_id"),
        @Index(name = "fk_customer_address1_idx", columnList = "address_id"),
        @Index(name = "key_customer_name", columnList = "name"),
        @Index(name = "key_customer_lastname", columnList = "lastname")
    },
    uniqueConstraints = {
        @UniqueConstraint(name = "unique_customer_identification_number", columnNames = "identification_number"),
        @UniqueConstraint(name = "email_unique", columnNames = "email"),
        @UniqueConstraint(name = "phone_number_unique", columnNames = "phone_number"),
    }
)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 100)
    private String lastname;

    @Column(nullable = false, length = 60, unique = true)
    private String identificationNumber;

    @Column(nullable = false)
    private Date dateOfBirth;

    @Column(nullable = false, length = 60, unique = true)
    private String phoneNumber;

    @Column(nullable = false, length = 100, unique = true)
    private String email;

    @ManyToOne
    @JoinColumn(name = "customer_type_id", nullable = false, foreignKey = @ForeignKey(name = "fk_customer_customer_type1"))
    private CustomerType customerType;

    @OneToOne
    @JoinColumn(name = "address_Id", nullable = false, foreignKey = @ForeignKey(name = "fk_customer_address1"))
    private Address address;
}
