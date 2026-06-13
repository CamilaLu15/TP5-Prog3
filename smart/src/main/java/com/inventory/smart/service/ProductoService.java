package com.inventory.smart.service;

import com.inventory.smart.model.Producto;
import java.util.List;

/**
 * Servicio encargado de la lógica de negocio para la gestión de productos.
 *
 * @author Grupo Integrador
 * @since 1.0
 */
public interface ProductoService {
    /**
     * Obtiene todos los productos del catálogo.
     *
     * @return lista de productos
     */
    List<Producto> findAll();

    /**
     * Busca un producto por su identificador único.
     *
     * @param id identificador del producto
     * @return el producto encontrado
     * @throws com.inventory.smart.exception.ResourceNotFoundException si no existe
     */
    Producto findById(Long id);

    /**
     * Persiste un nuevo producto asegurando que el stock inicial no sea negativo.
     *
     * @param producto el producto a guardar
     * @return el producto persistido con su ID asignado
     * @throws com.inventory.smart.exception.BusinessRuleException si el stock inicial es negativo
     */
    Producto save(Producto producto);

    /**
     * Elimina un producto por su identificador.
     *
     * @param id identificador del producto a eliminar
     */
    void deleteById(Long id);
}
