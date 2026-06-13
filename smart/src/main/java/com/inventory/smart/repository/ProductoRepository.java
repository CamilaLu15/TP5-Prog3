package com.inventory.smart.repository;

import com.inventory.smart.model.Producto;
import java.util.List;

/**
 * Contrato específico para el acceso a datos de los Productos.
 *
 * @author Grupo Integrador
 * @since 1.0
 */

public interface ProductoRepository extends IGenericRepository<Producto, Long> {
    /**
     * Obtiene todos los productos asociados a una categoría específica.
     *
     * @param categoriaId el ID de la categoría a filtrar
     * @return lista de productos coincidentes
     */
    List<Producto> findByCategoria(Long categoriaId);

    /**
     * Busca productos cuyo nombre contenga la cadena proporcionada.
     *
     * @param texto texto a buscar, sin distinguir mayúsculas
     * @return lista de productos que coinciden con el texto
     */
    List<Producto> buscarPorNombre(String texto);
}
