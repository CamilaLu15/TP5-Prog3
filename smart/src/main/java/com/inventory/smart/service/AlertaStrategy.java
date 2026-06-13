package com.inventory.smart.service;

import com.inventory.smart.model.Producto;
import com.inventory.smart.model.NivelAlerta;
import java.util.Optional;

/**
 * Define el contrato para el patrón Strategy aplicado a las reglas de alerta de stock.
 *
 * @author Grupo Integrador
 * @since 1.0
 */
public interface AlertaStrategy {
    /**
     * Evalúa si un producto cumple con las condiciones de esta estrategia de alerta.
     *
     * @param producto el producto a evaluar
     * @return un Optional conteniendo el NivelAlerta si aplica, o vacío si no aplica
     */
    Optional<NivelAlerta> evaluar(Producto producto);
}
