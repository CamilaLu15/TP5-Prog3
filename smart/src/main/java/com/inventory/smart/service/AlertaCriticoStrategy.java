package com.inventory.smart.service;

import com.inventory.smart.config.StockConfig;
import com.inventory.smart.model.NivelAlerta;
import com.inventory.smart.model.Producto;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Estrategia que evalúa si un producto se encuentra en estado CRITICO.
 * Tiene prioridad alta (Order 1) para evaluarse primero.
 *
 * @author Grupo Integrador
 * @since 1.0
 */
@Component
@Order(1)

public class AlertaCriticoStrategy implements AlertaStrategy {
    private final StockConfig config;

    /**
     * Constructor con inyección de dependencias.
     *
     * @param config la configuración de umbrales
     */
    public AlertaCriticoStrategy(StockConfig config) {
        this.config = config;
    }

    @Override
    public Optional<NivelAlerta> evaluar(Producto producto) {
        if (producto.getStock() < config.critico()) {
            return Optional.of(NivelAlerta.CRITICO);
        }
        return Optional.empty();
    }
}
