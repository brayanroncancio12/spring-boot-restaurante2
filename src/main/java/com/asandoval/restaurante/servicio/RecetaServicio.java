package com.asandoval.restaurante.servicio;

import com.asandoval.restaurante.modelos.Receta;
import com.asandoval.restaurante.repositorio.IRecetaRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class RecetaServicio {
    IRecetaRepositorio recetaRepositorio;

    public RecetaServicio(IRecetaRepositorio recetaRepositorio){
        this.recetaRepositorio = recetaRepositorio;
    }

    public Receta prepararPlato(){
        List<Receta> recetas = recetaRepositorio.findAll();
        if (recetas.isEmpty()) {
            throw new IllegalStateException("No hay recetas disponibles.");
        }

        Random random = new Random();
        int randomIndex = random.nextInt(recetas.size());
        return recetas.get(randomIndex);
    }

    public List<Receta> obtenerTodasLasRecetas() {
        return recetaRepositorio.findAll();
    }
}

