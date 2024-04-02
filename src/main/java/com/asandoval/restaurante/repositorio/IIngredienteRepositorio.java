package com.asandoval.restaurante.repositorio;

import com.asandoval.restaurante.modelos.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IIngredienteRepositorio extends JpaRepository<Ingrediente, Long> {
    Ingrediente findByName(String nombreIngrediente);
}
