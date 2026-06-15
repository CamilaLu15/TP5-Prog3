package com.inventory.smart.service;

import com.inventory.smart.exception.InsufficientStockException;
import com.inventory.smart.model.MovimientoInventario;
import com.inventory.smart.model.Producto;
import com.inventory.smart.model.TipoMovimiento;
import com.inventory.smart.repository.MovimientoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Implementación de la lógica de negocio para el registro y consulta de movimientos de inventario.
 * <p>Esta clase orquesta la validación de stock y delega la actualización atómica
 * del mismo a la entidad Producto.</p>
 *
 * @author Grupo Integrador
 * @since 1.0
 */
@Service
public class MovimientoServiceImpl implements MovimientoService {

    private final MovimientoRepository movimientoRepository;
    private final ProductoService productoService;

    /**
     * Constructor para la inyección de dependencias.
     *
     * @param movimientoRepository repositorio de movimientos
     * @param productoService      servicio de productos para validaciones cruzadas
     */

    public MovimientoServiceImpl(MovimientoRepository movimientoRepository, ProductoService productoService) {
        this.movimientoRepository = movimientoRepository;
        this.productoService = productoService;
    }

    @Override
    public MovimientoInventario registrarMovimiento(MovimientoInventario movimiento) {
    
        Producto producto = productoService.findById(movimiento.getProductoId());

       
        int stockResultante;
        
        if (movimiento.getTipo() == TipoMovimiento.ENTRADA) {
            stockResultante = producto.incrementarStock(movimiento.getCantidad());
        } else {
   
            if (producto.getStock() < movimiento.getCantidad()) {
                throw new InsufficientStockException("No se pueden retirar " + movimiento.getCantidad() + 
                        " unidades. Stock disponible: " + producto.getStock());
            }
            stockResultante = producto.decrementarStock(movimiento.getCantidad());
        }

  
        movimiento.setStockResultante(stockResultante);
        movimiento.setFecha(LocalDateTime.now());
        
        return movimientoRepository.save(movimiento);
    }

    @Override
    public List<MovimientoInventario> buscarPorProducto(Long productoId) {

        return movimientoRepository.findAll().stream()
                .filter(m -> m.getProductoId().equals(productoId))
                .toList();
    }
}