package com.inventory.smart.repository;

import com.inventory.smart.model.Producto;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Implementación en memoria para la persistencia de {@link Producto}.
 *
 * @author Grupo Integrador
 * @since 1.0
 */
@Repository

public class InMemoryProductoRepository extends GenericInMemoryRepository<Producto, Long> 
        implements ProductoRepository {

            @Override
    public List<Producto> findByCategoria(Long categoriaId) {
        return dataStore.values().stream()
                .filter(p -> p.getCategoria().getId().equals(categoriaId))
                .toList();
    }

    @Override
    public List<Producto> buscarPorNombre(String texto) {
        String lower = texto.toLowerCase();
        return dataStore.values().stream()
                .filter(p -> p.getNombre().toLowerCase().contains(lower))
                .toList();
    }

    @Override
    protected Long getEntityId(Producto entity) {
        return entity.getId();
    }

    @Override
    protected void setEntityId(Producto entity, Long id) {
        entity.setId(id);
    }
}
