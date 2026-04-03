package com.inmobiliaria.server.models;

import java.math.BigDecimal;
import java.sql.Date;
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
@Table(name = "appraisal",

        indexes = {
                @Index(name = "fk_appraisal_property1_idx", columnList = "property_id"),
                @Index(name = "fk_appraisal_money_type1_idx", columnList = "money_type_id")
        })
public class Appraisal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    @Column(nullable = false)
    private Date date;
    @Column(nullable = false, precision = 11, scale = 2)
    private BigDecimal value;

    @ManyToOne
    @JoinColumn(name = "property_id", nullable = false, foreignKey = @ForeignKey(name = "fk_appraisal_property"))
    private Property property;

    @ManyToOne
    @JoinColumn(name = "money_type_Id", nullable = false, foreignKey = @ForeignKey(name = "fk_appraisal_money_type1"))
    private MoneyType moneyType;
}
