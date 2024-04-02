package com.asandoval.restaurante.repositorio;

import com.asandoval.restaurante.modelos.Receta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRecetaRepositorio extends JpaRepository<Receta, Long> {
}
