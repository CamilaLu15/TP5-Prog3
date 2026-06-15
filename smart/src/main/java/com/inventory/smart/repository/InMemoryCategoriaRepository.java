package com.inventory.smart.repository;

import com.inventory.smart.model.Categoria;
import org.springframework.stereotype.Repository;

/**
 * Implementación en memoria para el repositorio de Categorías.
 * 
 * @author Grupo Integrador
 * @since 1.0
 */
@Repository
public class InMemoryCategoriaRepository 
        extends GenericInMemoryRepository<Categoria, Long> 
        implements CategoriaRepository {
                @Override
    protected Long getEntityId(Categoria entity) {
        return entity.getId();
    }

    @Override
    protected void setEntityId(Categoria entity, Long id) {
        entity.setId(id);
    }
}
