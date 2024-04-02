package com.asandoval.restaurante.controlador;

import com.asandoval.restaurante.modelos.Receta;
import com.asandoval.restaurante.servicio.RecetaServicio;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

@RestController
public class RestauranteControlador {
    private final RecetaServicio recetaServicio;

    public RestauranteControlador(RecetaServicio recetaServicio) {
        this.recetaServicio = recetaServicio;
    }

    @GetMapping("/platos")
    public List<Receta> verPlatos() {
        return recetaServicio.obtenerTodasLasRecetas();
    }

    @GetMapping("/platos/pedido")
    public Receta obtenerPlatoAleatorio() {
        List<Receta> recetas = recetaServicio.obtenerTodasLasRecetas();
        if (recetas.isEmpty()) {
            return null;
        }
        Random random = new Random();
        int indiceAleatorio = random.nextInt(recetas.size());
        return recetas.get(indiceAleatorio);
    }
}
