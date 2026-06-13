package com.inventory.smart.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Registro de configuración que mapea los umbrales de stock definidos en application.yml.
 *
 * @param minimo  el valor por debajo del cual un producto entra en alerta BAJO
 * @param critico el valor por debajo del cual un producto entra en alerta CRITICO
 * @author Grupo Integrador
 * @since 1.0
 */
@ConfigurationProperties(prefix = "inventario.stock")
public record StockConfig(int minimo, int critico) {

}
