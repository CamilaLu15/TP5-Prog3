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

    @Override
    public Producto update(Long id, Producto actualizado) {
        Producto existente = findById(id); // Esto ya lanza 404 si no existe
        
        existente.setNombre(actualizado.getNombre());
        existente.setDescripcion(actualizado.getDescripcion());
        existente.setPrecio(actualizado.getPrecio());
        existente.setCategoria(actualizado.getCategoria());
        // Nota: El stock no se actualiza por acá, se modifica vía movimientos.
        
        return productoRepository.save(existente);
    }

    @Override
    public List<Producto> buscarPorNombre(String q) {
        if (q == null || q.trim().isEmpty()) {
            throw new IllegalArgumentException("El parámetro de búsqueda no puede estar vacío");
        }
        return productoRepository.buscarPorNombre(q);
    }

    @Override
    public List<Producto> listarOrdenados(String campo, String orden) {
        List<Producto> productos = productoRepository.findAll();
        
        // Asignamos el comparador dinámicamente
        java.util.Comparator<Producto> comparator = switch (campo.toLowerCase()) {
            case "precio" -> java.util.Comparator.comparing(Producto::getPrecio);
            case "stock" -> java.util.Comparator.comparing(Producto::getStock);
            default -> java.util.Comparator.comparing(Producto::getNombre);
        };

        // Si el orden es descendente, invertimos el comparador
        if ("desc".equalsIgnoreCase(orden)) {
            comparator = comparator.reversed();
        }

        // List.sort() / stream().sorted() utiliza TimSort por debajo, que es O(n log n)
        return productos.stream().sorted(comparator).toList();
    }
}
