package com.inventory.smart.exception;

/**
 * Excepción lanzada cuando se intenta retirar una cantidad de stock superior a la disponible.
 *
 * @author Grupo Integrador
 * @since 1.0
 */

public class InsufficientStockException extends RuntimeException {
    /**
     * Construye una nueva excepción indicando el stock disponible y el solicitado.
     *
     * @param message el mensaje descriptivo del error
     */
    public InsufficientStockException(String message) {
        super(message);
    }
}
