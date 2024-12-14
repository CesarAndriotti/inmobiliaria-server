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
@Table(name = "Construction",

    indexes = {
        @Index(name = "fk_Construction_Property1_idx", columnList = "property_Id"),
        @Index(name = "fk_Construction_Construction_Type1_idx", columnList = "construction_Type_Id")
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
    @JoinColumn(name = "property_Id", nullable = false, foreignKey = @ForeignKey(name = "fk_Construction_Property1"))
    private Property property;

    @ManyToOne
    @JoinColumn(name = "construction_Type_Id", nullable = false, foreignKey = @ForeignKey(name = "fk_Construction_Construction_Type1"))
    private ConstructionType construction_type;
}
