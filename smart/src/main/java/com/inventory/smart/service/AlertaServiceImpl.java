package com.inventory.smart.service;

import com.inventory.smart.dto.AlertaStockResponse;
import com.inventory.smart.model.NivelAlerta;
import com.inventory.smart.model.Producto;
import com.inventory.smart.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementación del servicio de alertas utilizando el patrón Strategy.
 *
 * @author Grupo Integrador
 * @since 1.0
 */
@Service
public class AlertaServiceImpl implements AlertaService {
    private final ProductoRepository productoRepository;
    private final List<AlertaStrategy> estrategias;

    public AlertaServiceImpl(ProductoRepository productoRepository, List<AlertaStrategy> estrategias) {
        this.productoRepository = productoRepository;
        this.estrategias = estrategias;
    }

    @Override
    public List<AlertaStockResponse> obtenerProductosEnAlerta() {
        List<AlertaStockResponse> alertas = new ArrayList<>();
        
        for (Producto producto : productoRepository.findAll()) {
            for (AlertaStrategy estrategia : estrategias) {
                Optional<NivelAlerta> nivel = estrategia.evaluar(producto);
                if (nivel.isPresent()) {
                    alertas.add(new AlertaStockResponse(
                            producto.getId(), 
                            producto.getNombre(), 
                            producto.getStock(), 
                            nivel.get()
                    ));
                    break; // Si ya entró en una alerta (ej. CRÍTICO), no evaluamos la siguiente
                }
            }
        }
        return alertas;
    }
}
