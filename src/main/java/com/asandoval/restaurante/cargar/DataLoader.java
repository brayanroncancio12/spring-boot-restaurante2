package com.asandoval.restaurante.cargar;

import com.asandoval.restaurante.modelos.Ingrediente;
import com.asandoval.restaurante.modelos.Receta;
import com.asandoval.restaurante.repositorio.IIngredienteRepositorio;
import com.asandoval.restaurante.repositorio.IRecetaRepositorio;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    private final IRecetaRepositorio recetaRepositorio;
    private final IIngredienteRepositorio ingredienteRepositorio;

    public DataLoader(IRecetaRepositorio recetaRepositorio, IIngredienteRepositorio ingredienteRepositorio) {
        this.recetaRepositorio = recetaRepositorio;
        this.ingredienteRepositorio = ingredienteRepositorio;
    }

    @Override
    public void run(String... args) throws Exception {
        if (ingredienteRepositorio.count() == 0 && recetaRepositorio.count() == 0) {
            // Solo carga los ingredientes y recetas si la base de datos está vacía

            // Crear y guardar los ingredientes
            createAndSaveIngredient("Tomato", 5);
            createAndSaveIngredient("Lemon", 5);
            createAndSaveIngredient("Potato", 5);
            createAndSaveIngredient("Rice", 5);
            createAndSaveIngredient("Ketchup", 5);
            createAndSaveIngredient("Lettuce", 5);
            createAndSaveIngredient("Onion", 5);
            createAndSaveIngredient("Cheese", 5);
            createAndSaveIngredient("Meat", 5);
            createAndSaveIngredient("Chicken", 5);

            // Crear y guardar las recetas
            createAndSaveRecipe("Ensalada de Tomate, Papa y Cebolla", "Tomato", "Potato", "Onion");
            createAndSaveRecipe("Pollo con Papa", "Tomato", "Potato", "Chicken");
            createAndSaveRecipe("Arroz con Carne", "Rice", "Meat");
            createAndSaveRecipe("Ensalada Mixta", "Lettuce", "Onion", "Cheese");
            createAndSaveRecipe("Arroz Mandarin", "Tomato", "Lemon", "Potato", "Rice", "Ketchup", "Lettuce", "Onion", "Cheese", "Meat", "Chicken");
            createAndSaveRecipe("Ensalada de Tomate y Papa", "Tomato", "Potato", "Lettuce");
        }
    }

    private void createAndSaveIngredient(String name, int quantity) {
        Ingrediente ingrediente = new Ingrediente();
        ingrediente.setName(name);
        ingrediente.setCantidadDisponible(quantity);
        ingredienteRepositorio.save(ingrediente);
    }

    private void createAndSaveRecipe(String nombre, String... ingredientNames) {
        Receta receta = new Receta();
        receta.setNombre(nombre);

        for (String ingredientName : ingredientNames) {
            Ingrediente ingrediente = ingredienteRepositorio.findByName(ingredientName);
            if (ingrediente != null) {
                receta.getIngredientes().add(ingrediente);
            }
        }

        recetaRepositorio.save(receta);
    }
}
