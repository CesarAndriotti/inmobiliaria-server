package com.inmobiliaria.server.models;

import java.math.BigDecimal;

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
@Table(name = "Price",
    
    indexes = {
        @Index(name = "fk_Price_MoneyType1_idx", columnList = "Money_Type_Id"),
        @Index(name = "fk_Price_Property1_idx", columnList = "Publication_Id")
    }
)
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    @Column(nullable = false, precision = 6, scale = 2)
    private BigDecimal price;

    @Column(nullable = false)
    private Float profitPercentage;

    @ManyToOne
    @JoinColumn(name = "property_id", nullable = false, foreignKey = @ForeignKey(name = "fk_Price_Publication1"))
    private Publication publication;

    @ManyToOne
    @JoinColumn(name = "money_type_Id", nullable = false, foreignKey = @ForeignKey(name = "fk_Price_MoneyType1"))
    private MoneyType moneyType;
}

    