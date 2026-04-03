package com.inmobiliaria.server.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ForeignKey;
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
@Table(name = "property_multimedia",
    
    indexes = {
        @Index(name = "fk_property_multimedia_property1_idx", columnList = "property_id"),
        @Index(name = "fk_property_multimedia_multimedia_type1_idx", columnList = "multimedia_type_id")
    }
)
public class PropertyMultimedia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Integer id;

    @Column(length = 500, nullable = false)
    private String link;

    @ManyToOne
    @JoinColumn(name = "property_id", nullable = false, foreignKey = @ForeignKey(name = "fk_property_multimedia_property1"))
    private Property property;

    @ManyToOne
    @JoinColumn(name = "multimedia_type_id", nullable = false, foreignKey = @ForeignKey(name = "fk_property_multimedia_multimedia_type1"))
    private MultimediaType multimedia_type;
}
