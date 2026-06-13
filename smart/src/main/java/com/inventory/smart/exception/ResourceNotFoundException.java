package com.inventory.smart.exception;

/**
 * Excepción lanzada cuando no se encuentra un recurso solicitado en el repositorio.
 *
 * @author Grupo Integrador
 * @since 1.0
 */

public class ResourceNotFoundException extends RuntimeException {
    /**
     * Construye una nueva excepción con el mensaje especificado.
     *
     * @param message el mensaje de detalle descriptivo
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
