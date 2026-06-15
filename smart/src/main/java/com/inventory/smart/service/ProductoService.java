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

    /**
     * Actualiza los datos de un producto existente.
     *
     * @param id el identificador del producto a actualizar
     * @param productoActualizado los nuevos datos del producto
     * @return el producto actualizado
     * @throws com.inventory.smart.exception.ResourceNotFoundException si no existe el producto
     */
    Producto update(Long id, Producto productoActualizado);

    /**
     * Busca productos cuyo nombre coincida parcialmente con el texto indicado.
     *
     * @param q el texto a buscar
     * @return lista de productos coincidentes
     */
    List<Producto> buscarPorNombre(String q);

    /**
     * Devuelve la lista de productos ordenada según el campo y la dirección especificada.
     *
     * @param campo el atributo por el cual ordenar (nombre, precio, stock)
     * @param orden la dirección del ordenamiento (asc o desc)
     * @return lista de productos ordenada
     */
    List<Producto> listarOrdenados(String campo, String orden);
}
