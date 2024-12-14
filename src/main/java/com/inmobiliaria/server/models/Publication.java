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
@Table(name = "Publication",

    indexes = {
        @Index(name = "fk_Publication_Property1_idx", columnList = "property_Id"),
        @Index(name = "fk_Publication_Publication_State1_idx", columnList = "publication_State_Id"),
        @Index(name = "fk_Publication_Publication_Type1_idx", columnList = "publication_Type_Id")
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
    @JoinColumn(name = "property_Id", nullable = false, foreignKey = @ForeignKey(name = "fk_Publication_Property1"))
    private Property property;

    @ManyToOne
    @JoinColumn(name = "publication_State_Id", nullable = false, foreignKey = @ForeignKey(name = "fk_Publication_Publication_State1"))
    private PublicationState publication_state;

    @ManyToOne
    @JoinColumn(name = "publication_Type_Id", nullable = false, foreignKey = @ForeignKey(name = "fk_Publication_Publication_Type1"))
    private Publication_Type publication_type;
}
