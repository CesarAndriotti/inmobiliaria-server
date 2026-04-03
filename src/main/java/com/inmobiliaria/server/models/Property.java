package com.inmobiliaria.server.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Column;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "property",

                indexes = {
                                @Index(name = "fk_property_property_type1_idx", columnList = "property_type_id"),
                                @Index(name = "fk_property_address1_idx", columnList = "address_id"),
                                @Index(name = "fk_property_agent1_idx", columnList = "agent_id"),
                                @Index(name = "fk_property_property_state1_idx", columnList = "property_state_id")
                })
public class Property {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(nullable = false)
        private Integer id;

        @Column(nullable = false)
        private Float total_area;

        @Column(nullable = false)
        private Boolean water;

        @Column(nullable = false)
        private Boolean electricity;

        @Column(nullable = false)
        private Boolean gas;

        @Column(nullable = false)
        private Boolean sewer;

        @Column(nullable = false)
        private Boolean asphalt;

        @Column(nullable = false, length = 500)
        private String summary;

        @ManyToOne
        @JoinColumn(name = "property_type_id", nullable = false, foreignKey = @ForeignKey(name = "fk_property_property_type1"))
        private PropertyType propertyType;

        @OneToOne
        @JoinColumn(name = "address_id", nullable = false, foreignKey = @ForeignKey(name = "fk_property_address1"))
        private Address address;

        @ManyToOne
        @JoinColumn(name = "agent_id", nullable = false, foreignKey = @ForeignKey(name = "fk_property_agent1"))
        private Agent agent;

        @ManyToOne
        @JoinColumn(name = "property_state_id", nullable = false, foreignKey = @ForeignKey(name = "fk_property_property_state1"))
        private PropertyState propertyState;
}
