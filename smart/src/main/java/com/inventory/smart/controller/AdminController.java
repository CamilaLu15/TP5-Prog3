package com.inventory.smart.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Controlador para operaciones administrativas y de rendimiento.
 *
 * @author Grupo Integrador
 * @since 1.0
 */
@RestController
@RequestMapping("/api/admin")
public class AdminController {
    /**
     * Genera un reporte empírico de rendimiento simulando accesos.
     * @return reporte JSON con los tiempos
     */
    @GetMapping("/performance-report")
    public ResponseEntity<Map<String, Object>> getPerformanceReport() {
        Map<String, Object> reporte = new HashMap<>();
        
        // Simulación de medición O(n) iterando 100k veces
        long start = System.nanoTime();
        long sum = 0;
        for(int i = 0; i < 100000; i++) sum += i; 
        long end = System.nanoTime();

        reporte.put("mensaje", "Reporte generado exitosamente usando System.nanoTime()");
        reporte.put("resultado_simulacion", sum);
        reporte.put("tiempoOperacionBase_100k_ns", (end - start));
        reporte.put("complejidad_GET_productos", "O(n)");
        reporte.put("complejidad_GET_productos_id", "O(1)");
        
        return ResponseEntity.ok(reporte);
    }
}
