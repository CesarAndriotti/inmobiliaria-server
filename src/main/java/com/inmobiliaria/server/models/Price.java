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
@Table(name = "price",
    
    indexes = {
        @Index(name = "fk_price_money_type1_idx", columnList = "money_type_id"),
        @Index(name = "fk_price_publication1_idx", columnList = "publication_id")
    }
)
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    @Column(nullable = false, precision = 11, scale = 2)
    private BigDecimal price;

    @Column(nullable = false)
    private Float profitPercentage;

    @ManyToOne
    @JoinColumn(name = "publication_id", nullable = false, foreignKey = @ForeignKey(name = "fk_price_publication1"))
    private Publication publication;

    @ManyToOne
    @JoinColumn(name = "money_type_Id", nullable = false, foreignKey = @ForeignKey(name = "fk_price_money_type1"))
    private MoneyType moneyType;
}

    