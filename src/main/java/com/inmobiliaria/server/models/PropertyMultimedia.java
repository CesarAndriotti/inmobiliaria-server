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
@Table(name = "Property_Multimedia",
    
    indexes = {
        @Index(name = "fk_Property_Multimedia_Property1_idx", columnList = "property_Id"),
        @Index(name = "fk_Property_Multimedia_Multimedia_Type1_idx", columnList = "multimedia_Type_Id")
    }
)
public class PropertyMultimedia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false, unique = true)
    private Long id;

    @Column(name = "Link", length = 500, nullable = false)
    private String link;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "property_Id", nullable = false, foreignKey = @ForeignKey(name = "fk_Property_Multimedia_Property1"))
    private Property property;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "multimedia_Type_Id", nullable = false, foreignKey = @ForeignKey(name = "fk_Property_Multimedia_Multimedia_Type1"))
    private MultimediaType multimedia_type;
}
