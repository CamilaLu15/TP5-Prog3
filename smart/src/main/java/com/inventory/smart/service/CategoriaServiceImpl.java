package com.inventory.smart.service;

import com.inventory.smart.exception.BusinessRuleException;
import com.inventory.smart.exception.ResourceNotFoundException;
import com.inventory.smart.model.Categoria;
import com.inventory.smart.repository.CategoriaRepository;
import com.inventory.smart.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementación de la lógica de negocio para las categorías.
 * <p>Aplica las validaciones de integridad referencial antes de permitir
 * la eliminación de registros.</p>
 *
 * @author Grupo Integrador
 * @since 1.0
 */
@Service
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final ProductoRepository productoRepository;

    /**
     * Constructor para la inyección de dependencias.
     *
     * @param categoriaRepository repositorio de categorías
     * @param productoRepository  repositorio de productos para validaciones de integridad
     */
   
    public CategoriaServiceImpl(CategoriaRepository categoriaRepository, ProductoRepository productoRepository) {
        this.categoriaRepository = categoriaRepository;
        this.productoRepository = productoRepository;
    }

    @Override
    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    @Override
    public Categoria findById(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró la categoría con ID: " + id));
    }

    @Override
    public Categoria save(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    @Override
    public Categoria update(Long id, Categoria categoriaActualizada) {
        Categoria categoria = findById(id);
        categoria.setNombre(categoriaActualizada.getNombre());
        categoria.setDescripcion(categoriaActualizada.getDescripcion());
        return categoriaRepository.save(categoria);
    }

    @Override
    public void deleteById(Long id) {
        if (!categoriaRepository.existsById(id)) {
            throw new ResourceNotFoundException("No se puede eliminar: No se encontró la categoría con ID: " + id);
        }

   
        if (!productoRepository.findByCategoria(id).isEmpty()) {
            throw new BusinessRuleException("No se puede eliminar la categoría porque tiene productos asociados.");
        }

        categoriaRepository.deleteById(id);
    }
}