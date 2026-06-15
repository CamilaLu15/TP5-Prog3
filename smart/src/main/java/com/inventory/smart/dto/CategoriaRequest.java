package com.inventory.smart.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * DTO de petición utilizado para la creación y actualización de categorías.
 * <p>Aplica validaciones de entrada para garantizar que los datos esenciales,
 * como el nombre, estén presentes y no vacíos antes de llegar a la capa de negocio.</p>
 *
 * @param nombre      el nombre descriptivo de la categoría (obligatorio)
 * @param descripcion los detalles adicionales de la categoría (opcional)
 * @author Grupo Integrador
 * @since 1.0
 */
public record CategoriaRequest(
        @NotBlank(message = "El nombre de la categoría es obligatorio")
        String nombre,
        String descripcion
) {}

