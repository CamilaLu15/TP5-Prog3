package com.inventory.smart.repository;

import com.inventory.smart.model.MovimientoInventario;
import org.springframework.stereotype.Repository;

/**
 * Implementación en memoria para la persistencia de {@link MovimientoInventario}.
 *
 * @author Grupo Integrador
 * @since 1.0
 */
@Repository
public class InMemoryMovimientoRepository 
        extends GenericInMemoryRepository<MovimientoInventario, Long> 
        implements MovimientoRepository {

        @Override
    protected Long getEntityId(MovimientoInventario entity) {
        return entity.getId();
    }

    @Override
    protected void setEntityId(MovimientoInventario entity, Long id) {
        entity.setId(id);
    }
}