package com.inventory.smart.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

/**
 * DTO de petición utilizado para la creación y actualización de productos.
 * <p>Implementa Jakarta Bean Validation para garantizar la integridad de los datos de entrada
 * antes de que alcancen la capa de servicios.</p>
 *
 * @param nombre       el nombre del producto, obligatorio y con longitud entre 2 y 100 caracteres
 * @param descripcion  los detalles adicionales del producto, máximo 500 caracteres
 * @param precio       el precio unitario, que debe ser mayor o igual a 0
 * @param stockInicial la cantidad inicial de unidades, que debe ser mayor o igual a 0
 * @param categoriaId  el identificador único de la categoría a la que pertenece, obligatorio
 * @author Grupo Integrador
 * @since 1.0
 */
public record ProductoRequest(
        @NotBlank(message = "El nombre es obligatorio")
        @Size(min = 2, max = 100)
        String nombre,

        @Size(max = 500)
        String descripcion,

        @PositiveOrZero(message = "El precio debe ser >= 0")
        double precio,

        @PositiveOrZero(message = "El stock inicial debe ser >= 0")
        int stockInicial,

        @NotNull(message = "La categoría es obligatoria")
        Long categoriaId
) {}
