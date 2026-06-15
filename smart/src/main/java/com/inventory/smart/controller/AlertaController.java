package com.inventory.smart.controller;

import com.inventory.smart.dto.AlertaStockResponse;
import com.inventory.smart.service.AlertaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controlador REST para consultar las alertas del sistema.
 *
 * @author Grupo Integrador
 * @since 1.0
 */
@RestController
@RequestMapping("/api/alertas")
public class AlertaController {
    private final AlertaService alertaService;

    public AlertaController(AlertaService alertaService) {
        this.alertaService = alertaService;
    }

    /**
     * Retorna los productos cuyo stock está por debajo del mínimo.
     * @return respuesta HTTP 200 con la lista de alertas
     */
    @GetMapping("/stock-bajo")
    public ResponseEntity<List<AlertaStockResponse>> stockBajo() {
        return ResponseEntity.ok(alertaService.obtenerProductosEnAlerta());
    }
}
