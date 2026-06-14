package com.inventory.smart.service;

import com.inventory.smart.model.Categoria;
import java.util.List;

/**
 * Servicio para la gestión de las Categorías del inventario.
 *
 * @author Grupo Integrador
 * @since 1.0
 */
public interface CategoriaService {
    List<Categoria> findAll();
    Categoria findById(Long id);
    Categoria save(Categoria categoria);
    Categoria update(Long id, Categoria categoria);
    void deleteById(Long id);
}