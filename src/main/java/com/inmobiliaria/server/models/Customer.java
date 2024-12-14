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
@Table(name = "Customer",
    
    indexes = {
        @Index(name = "fk_Customer_Customer_Type1_idx", columnList = "customer_Type_Id"),
        @Index(name = "fk_Customer_Address1_idx", columnList = "address_Id"),
        @Index(name = "key_Customer_Name", columnList = "name"),
        @Index(name = "key_Customer_Lastname", columnList = "lastname")
    },
    uniqueConstraints = {
        @UniqueConstraint(name = "unique_Customer_IdentificationNumber", columnNames = "identificationNumber")
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

    @Column(nullable = false, length = 60, unique = true)
    private String email;

    @ManyToOne
    @JoinColumn(name = "customer_Type_Id", nullable = false, foreignKey = @ForeignKey(name = "fk_Customer_Customer_Type1"))
    private CustomerType customerType;

    @OneToOne
    @JoinColumn(name = "address_Id", nullable = false, foreignKey = @ForeignKey(name = "fk_Customer_Address1"))
    private Address address;
}
