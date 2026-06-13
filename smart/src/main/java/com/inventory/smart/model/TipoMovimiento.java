package com.inventory.smart.model;

/**
 * Define la dirección de un movimiento de inventario.
 * * @author Grupo Integrador
 * @since 1.0
 */

public enum TipoMovimiento {
    /** Representa un incremento en el stock del producto. */
    ENTRADA,
    
    /** Representa un decremento en el stock del producto. */
    SALIDA
}
