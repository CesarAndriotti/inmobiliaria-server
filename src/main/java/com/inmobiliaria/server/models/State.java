package com.inmobiliaria.server.models;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;;

@Entity
@Data
@AllArgsConstructor  
@NoArgsConstructor 
@Table(name = "State", 
    
    indexes = {
        @Index(name = "fk_State_Country1_idx", columnList = "Country_Id")
    }
)
public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)  
    private Integer id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false, foreignKey = @ForeignKey(name = "fk_State_Country1")) 
    private Country country;

    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return Objects.equals(id, state.id) &&
            Objects.equals(name, state.name) &&
            Objects.equals(country, state.country); // Asegúrate de que `country` también tenga equals()
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, country);
    }*/
}
