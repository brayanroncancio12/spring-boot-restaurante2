package com.asandoval.restaurante.modelos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Receta {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    Long Id;
    String nombre;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "receta_ingrediente",
            joinColumns = @JoinColumn(name = "receta_id"),
            inverseJoinColumns = @JoinColumn(name = "ingrediente_id")
    )
    private Set<Ingrediente> ingredientes = new HashSet<>();

}
