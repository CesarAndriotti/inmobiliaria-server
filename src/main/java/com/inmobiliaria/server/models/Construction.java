package com.inmobiliaria.server.models;

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
import jakarta.persistence.Column;

@Entity
@Data
@AllArgsConstructor  
@NoArgsConstructor 
@Table(name = "construction",

    indexes = {
        @Index(name = "fk_construction_property1_idx", columnList = "property_id"),
        @Index(name = "fk_construction_construction_type1_idx", columnList = "construction_type_id")
    }
)
public class Construction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    @Column(nullable = false)
    private Float covered_area;

    @Column(nullable = false)
    private Byte age;

    @Column(nullable = false)
    private Byte rooms;

    @Column(nullable = false)
    private Byte offices;

    @Column(nullable = false)
    private Byte bedrooms;

    @Column(nullable = false)
    private Byte bathrooms;

    @Column(nullable = false)
    private Byte garage;

    @Column(nullable = false)
    private Byte pools;

    @Column(nullable = false)
    private Byte barbecue;

    @ManyToOne
    @JoinColumn(name = "property_id", nullable = false, foreignKey = @ForeignKey(name = "fk_construction_property1"))
    private Property property;

    @ManyToOne
    @JoinColumn(name = "construction_type_id", nullable = false, foreignKey = @ForeignKey(name = "fk_construction_construction_type1"))
    private ConstructionType construction_type;
}
