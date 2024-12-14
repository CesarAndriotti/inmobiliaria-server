package com.inmobiliaria.server.models;

import java.math.BigDecimal;
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
@Table(name = "Historical_Prices",

    indexes = {
        @Index(name = "fk_Historical_Prices_Price1_idx", columnList = "price_Id")
    }
)
public class HistoricalPrices {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false, precision = 6, scale = 2)
    private BigDecimal previous_price;

    @Column(nullable = false, precision = 6, scale = 2)
    private BigDecimal afterPrice;

    @Column(nullable = false)
    private Float previous_profit_percentage;

    @Column(nullable = false)
    private Float after_profit_percentage;
    
    @ManyToOne
    @JoinColumn(name = "price_Id", nullable = false, foreignKey = @ForeignKey(name = "fk_Historical_Prices_Price1"))
    private Price price;
}
