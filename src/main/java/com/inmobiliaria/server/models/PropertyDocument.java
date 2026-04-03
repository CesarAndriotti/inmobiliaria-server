package com.inmobiliaria.server.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor  
@NoArgsConstructor 
@Table(name = "property_document", 

    indexes = {
        @Index(name = "fk_property_document_property1_idx", columnList = "property_id")
    }
)
public class PropertyDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    @Column(nullable = false, length = 500)
    private String link;

    @OneToOne
    @JoinColumn(name = "property_id", nullable = false, foreignKey = @ForeignKey(name = "fk_property_document_property1"))
    private Property property;
}
