package com.inmobiliaria.server.models;

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
@Table(name = "publication",

    indexes = {
        @Index(name = "fk_publication_property1_idx", columnList = "property_id"),
        @Index(name = "fk_publication_publication_state1_idx", columnList = "publication_state_id"),
        @Index(name = "fk_publication_publication_type1_idx", columnList = "publication_type_id")
    }
)
public class Publication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    @Column(nullable = false)
    private Date start_date;

    @Column(nullable = false)
    private Date finish_date;

    @ManyToOne
    @JoinColumn(name = "property_id", nullable = false, foreignKey = @ForeignKey(name = "fk_publication_property1"))
    private Property property;

    @ManyToOne
    @JoinColumn(name = "publication_state_id", nullable = false, foreignKey = @ForeignKey(name = "fk_publication_publication_state1"))
    private PublicationState publication_state;

    @ManyToOne
    @JoinColumn(name = "publication_type_id", nullable = false, foreignKey = @ForeignKey(name = "fk_publication_publication_type1"))
    private Publication_Type publication_type;
}
