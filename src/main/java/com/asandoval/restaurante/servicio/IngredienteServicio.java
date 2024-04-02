package com.asandoval.restaurante.servicio;

import com.asandoval.restaurante.modelos.Ingrediente;
import com.asandoval.restaurante.repositorio.IIngredienteRepositorio;
import org.springframework.stereotype.Service;

@Service
public class IngredienteServicio {
    IIngredienteRepositorio ingredienteRepositorio;

    public IngredienteServicio(IIngredienteRepositorio ingredienteRepositorio) {
        this.ingredienteRepositorio = ingredienteRepositorio;
    }

    public boolean disponibilidadIngrediente(String nombreIngrediente) {
        // Lógica para verificar si el ingrediente está disponible en la bodega
        Ingrediente ingrediente = ingredienteRepositorio.findByName(nombreIngrediente);
        return ingrediente != null && ingrediente.getCantidadDisponible() > 0;
    }

    public void orderIngredients(String ingredientName, int quantity) {
        // Lógica para ordenar ingredientes faltantes a la bodega
        Ingrediente ingrediente = ingredienteRepositorio.findByName(ingredientName);
        if (ingrediente != null) {
            // implementar la lógica para realizar un pedido al proveedor
            ingrediente.setCantidadDisponible(ingrediente.getCantidadDisponible() + quantity);
            ingredienteRepositorio.save(ingrediente);
        } else {
            // Manejar el caso cuando el ingrediente no está disponible en la bodega
            throw new IllegalArgumentException("El ingrediente especificado no está disponible en la bodega.");
        }
    }

    public void useIngredients(String nombreIngrediente, int cantidad) {
        // Lógica para utilizar ingredientes de la bodega
        // implementar la lógica para restar ingredientes utilizados
        Ingrediente ingrediente = ingredienteRepositorio.findByName(nombreIngrediente);
        if (ingrediente != null && ingrediente.getCantidadDisponible() >= cantidad) {
            // lógica para restar la cantidad utilizada de la bodega
            ingrediente.setCantidadDisponible(ingrediente.getCantidadDisponible() - cantidad);
            ingredienteRepositorio.save(ingrediente);
        } else {
            // Manejar el caso cuando no hay suficientes ingredientes disponibles en la bodega
            throw new IllegalArgumentException("No hay suficientes ingredientes disponibles en la bodega.");
        }
    }
}
