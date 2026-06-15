package com.inventory.smart.dto;

/**
 * DTO de respuesta que representa los datos de un producto expuestos a través de la API.
 * <p>Garantiza la inmutabilidad de la respuesta HTTP y aísla la entidad del modelo de dominio.</p>
 *
 * @param id          el identificador único del producto
 * @param nombre      el nombre descriptivo del producto
 * @param descripcion los detalles adicionales del producto
 * @param precio      el precio unitario
 * @param stock       la cantidad actual de unidades disponibles
 * @param categoria   el DTO con los datos de la categoría a la que pertenece
 * @author Grupo Integrador
 * @since 1.0
 */
public record ProductoResponse(
        Long id,
        String nombre,
        String descripcion,
        double precio,
        int stock,
        CategoriaResponse categoria
) {}
