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
    /**
     * Obtiene todas las categorías registradas en el sistema.
     *
     * @return una lista de categorías
     */
    List<Categoria> findAll();

    /**
     * Busca una categoría por su identificador único.
     *
     * @param id el identificador de la categoría
     * @return la categoría encontrada
     * @throws com.inventory.smart.exception.ResourceNotFoundException si no existe
     */
    Categoria findById(Long id);

    /**
     * Persiste una nueva categoría en el sistema.
     *
     * @param categoria la categoría a guardar
     * @return la categoría persistida con su ID generado
     */
    Categoria save(Categoria categoria);

    /**
     * Actualiza los datos de una categoría existente.
     *
     * @param id                   el identificador de la categoría a actualizar
     * @param categoriaActualizada objeto con los nuevos datos (nombre y descripción)
     * @return la categoría actualizada
     * @throws com.inventory.smart.exception.ResourceNotFoundException si no existe la categoría a actualizar
     */
    Categoria update(Long id, Categoria categoria);

    /**
     * Elimina una categoría del sistema validando previamente las reglas de negocio.
     *
     * @param id el identificador de la categoría a eliminar
     * @throws com.inventory.smart.exception.ResourceNotFoundException si no existe la categoría
     * @throws com.inventory.smart.exception.BusinessRuleException     si la categoría tiene productos asociados
     */
    void deleteById(Long id);
}