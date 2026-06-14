package com.inventory.smart.dto;

public record ProductoResponse(
        Long id,
        String nombre,
        String descripcion,
        double precio,
        int stock,
        CategoriaResponse categoria
) {}
