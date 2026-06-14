package com.inventory.smart.repository;

import com.inventory.smart.model.MovimientoInventario;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryMovimientoRepository 
        extends GenericInMemoryRepository<MovimientoInventario, Long> 
        implements MovimientoRepository {

}