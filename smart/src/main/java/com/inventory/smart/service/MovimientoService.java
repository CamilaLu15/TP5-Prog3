package com.inventory.smart.service;

import com.inventory.smart.model.MovimientoInventario;
import java.util.List;

/**
 * Servicio encargado de la lógica de negocio para los movimientos de inventario.
 *
 * @author Grupo Integrador
 * @since 1.0
 */
public interface MovimientoService {
    /**
     * Registra un nuevo movimiento de inventario (Entrada o Salida) actualizando el stock.
     *
     * @param movimiento el movimiento a registrar
     * @return el movimiento registrado con su stock resultante y fecha
     * @throws com.inventory.smart.exception.InsufficientStockException si no hay stock suficiente para una salida
     */

    MovimientoInventario registrarMovimiento(MovimientoInventario movimiento);

    /**
     * Busca el historial de movimientos asociados a un producto específico.
     *
     * @param productoId el identificador del producto
     * @return una lista con los movimientos del producto
     */
    
    List<MovimientoInventario> buscarPorProducto(Long productoId);
}