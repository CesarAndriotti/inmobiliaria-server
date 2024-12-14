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
@Table(name = "Property",
    
    indexes = {
        @Index(name = "fk_Property_PropertyType1_idx", columnList = "property_Type_Id"),
        @Index(name = "fk_Property_OperationType1_idx", columnList = "operation_Type_Id"),
        @Index(name = "fk_Property_Address1_idx", columnList = "address_Id"),
        @Index(name = "fk_Property_Agent1_idx", columnList = "agent_Id"),
        @Index(name = "fk_Property_PropertyState1_idx", columnList = "property_State_Id")
    }
)
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    @Column(nullable = false)
    private Float total_area;

    @Column(nullable = false)
    private Byte water;

    @Column(nullable = false)
    private Byte electricity;

    @Column(nullable = false)
    private Byte gas;

    @Column(nullable = false)
    private Byte sewer;

    @Column(nullable = false)
    private Byte asphalt;

    @Column(nullable = false, length = 500)
    private String summary;

    @ManyToOne
    @JoinColumn(name = "property_Type_Id", nullable = false, foreignKey = @ForeignKey(name = "fk_Property_Property_Type1"))
    private PropertyType property_type;

    @ManyToOne
    @JoinColumn(name = "operation_Type_Id", nullable = false, foreignKey = @ForeignKey(name = "fk_Property_Operation_Type1"))
    private OperationType operation_type;

    @OneToOne
    @JoinColumn(name = "address_Id", nullable = false, foreignKey = @ForeignKey(name = "fk_Property_Address1"))
    private Address address;

    @ManyToOne
    @JoinColumn(name = "agent_Id", nullable = false, foreignKey = @ForeignKey(name = "fk_Property_Agent1"))
    private Agent agent;

    @ManyToOne
    @JoinColumn(name = "property_State_Id", nullable = false, foreignKey = @ForeignKey(name = "fk_Property_Property_State1"))
    private PropertyState property_state;
}
