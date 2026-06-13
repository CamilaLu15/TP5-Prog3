package com.inventory.smart.exception;

/**
 * Excepción genérica para violaciones de las reglas de negocio del sistema
 * (ej. intentar eliminar una categoría con productos asociados).
 *
 * @author Grupo Integrador
 * @since 1.0
 */

public class BusinessRuleException extends RuntimeException {
    /**
     * Construye una nueva excepción de regla de negocio.
     *
     * @param message el mensaje descriptivo de la violación
     */
    public BusinessRuleException(String message) {
        super(message);
    }
}
