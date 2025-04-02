package com.inmobiliaria.server.models;

import java.util.Objects;

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
@Table(name = "Address", 

    indexes = {
        @Index(name = "fk_Address_City1_idx", columnList = "City_Id"),
    }
)
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "street_name", nullable = false, length = 100)
    private String streetName;

    @Column(nullable = false, length = 60)
    private String number;

    @ManyToOne
    @JoinColumn(name = "city_Id", nullable = false, foreignKey = @ForeignKey(name = "fk_Address_City1"))
    private City city;

    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(id, address.id) &&
            Objects.equals(streetName, address.streetName) &&
            Objects.equals(number, address.number) &&
            Objects.equals(city, address.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, streetName, number, city);
    }*/
}
