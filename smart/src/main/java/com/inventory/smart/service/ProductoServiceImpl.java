package com.inventory.smart.service;

import com.inventory.smart.exception.BusinessRuleException;
import com.inventory.smart.exception.ResourceNotFoundException;
import com.inventory.smart.model.Producto;
import com.inventory.smart.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementación de la lógica de negocio para productos.
 *
 * @author Grupo Integrador
 * @since 1.0
 */
@Service
public class ProductoServiceImpl implements ProductoService {
    private final ProductoRepository productoRepository;

    /**
     * Constructor para inyección de dependencias (Principio DIP).
     *
     * @param productoRepository repositorio de productos
     */
    public ProductoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    @Override
    public Producto findById(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con ID: " + id));
    }

    @Override
    public Producto save(Producto producto) {
        if (producto.getStock() < 0) {
            throw new BusinessRuleException("El stock de un producto no puede ser negativo.");
        }
        return productoRepository.save(producto);
    }

    @Override
    public void deleteById(Long id) {
        if (!productoRepository.existsById(id)) {
            throw new ResourceNotFoundException("No se puede eliminar. Producto no encontrado con ID: " + id);
        }
        productoRepository.deleteById(id);
    }
}
