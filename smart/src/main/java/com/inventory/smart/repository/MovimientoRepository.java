package com.inventory.smart.repository;

import com.inventory.smart.model.MovimientoInventario;

/**
 * Contrato específico para el acceso a datos de los Movimientos de Inventario.
 *
 * @author Grupo Integrador
 * @since 1.0
 */
public interface MovimientoRepository extends IGenericRepository<MovimientoInventario, Long> {
}