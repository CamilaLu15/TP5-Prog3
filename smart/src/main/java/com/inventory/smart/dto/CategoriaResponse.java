package com.inventory.smart.dto;

/**
 * DTO de respuesta que expone los datos de una categoría a través de la API REST.
 * <p>Permite enviar información plana y segura, evitando problemas de recursividad
 * o ciclos de serialización JSON al aislar la entidad del dominio.</p>
 *
 * @param id          el identificador único de la categoría
 * @param nombre      el nombre de la categoría
 * @param descripcion los detalles adicionales o descripción de la categoría
 * @author Grupo Integrador
 * @since 1.0
 */
public record CategoriaResponse(
        Long id,
        String nombre,
        String descripcion
) {}
