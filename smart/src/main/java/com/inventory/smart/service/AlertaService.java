package com.inventory.smart.service;

import com.inventory.smart.dto.AlertaStockResponse;
import java.util.List;

/**
 * Servicio para la gestión de alertas de stock.
 *
 * @author Grupo Integrador
 * @since 1.0
 */
public interface AlertaService {
    /**
     * Obtiene una lista de productos que se encuentran en alerta por stock bajo o crítico.
     * @return lista de respuestas de alerta
     */
    List<AlertaStockResponse> obtenerProductosEnAlerta();
}
