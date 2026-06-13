package com.inventory.smart.service;

import com.inventory.smart.config.StockConfig;
import com.inventory.smart.model.NivelAlerta;
import com.inventory.smart.model.Producto;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Estrategia que evalúa si un producto se encuentra en estado BAJO.
 * Se evalúa después de la estrategia crítica (Order 2).
 *
 * @author Grupo Integrador
 * @since 1.0
 */
@Component
@Order(2)

public class AlertaBajoStrategy implements AlertaStrategy {
    private final StockConfig config;

    /**
     * Constructor con inyección de dependencias.
     *
     * @param config la configuración de umbrales
     */
    public AlertaBajoStrategy(StockConfig config) {
        this.config = config;
    }

    @Override
    public Optional<NivelAlerta> evaluar(Producto producto) {
        if (producto.getStock() >= config.critico() && producto.getStock() < config.minimo()) {
            return Optional.of(NivelAlerta.BAJO);
        }
        return Optional.empty();
    }
}
