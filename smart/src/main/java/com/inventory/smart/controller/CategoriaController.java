package com.inventory.smart.controller;

import com.inventory.smart.dto.CategoriaRequest;
import com.inventory.smart.dto.CategoriaResponse;
import com.inventory.smart.model.Categoria;
import com.inventory.smart.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST encargado de gestionar las peticiones HTTP para las categorías.
 * <p>Maneja el CRUD completo delegando la lógica de negocio al servicio y
 * asegurando que la comunicación externa se realice exclusivamente mediante DTOs.</p>
 *
 * @author Grupo Integrador
 * @since 1.0
 */
@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    /**
     * Constructor para la inyección de dependencias.
     *
     * @param categoriaService servicio de lógica de negocio de categorías
     */
    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    /**
     * Lista todas las categorías disponibles en el sistema.
     *
     * @return respuesta HTTP 200 con la lista de categorías
     */
    @GetMapping
    public ResponseEntity<List<CategoriaResponse>> listarTodas() {
        List<CategoriaResponse> respuestas = categoriaService.findAll().stream()
                .map(this::mapearAResponse)
                .toList();
        return ResponseEntity.ok(respuestas);
    }

    /**
     * Obtiene una categoría específica por su identificador.
     *
     * @param id identificador único de la categoría
     * @return respuesta HTTP 200 con la categoría encontrada
     */
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponse> obtenerPorId(@PathVariable Long id) {
        Categoria categoria = categoriaService.findById(id);
        return ResponseEntity.ok(mapearAResponse(categoria));
    }

    /**
     * Crea una nueva categoría en el sistema.
     *
     * @param request payload con los datos de la categoría validados
     * @return respuesta HTTP 201 con los datos de la categoría creada
     */
    @PostMapping
    public ResponseEntity<CategoriaResponse> crear(@Valid @RequestBody CategoriaRequest request) {
        Categoria nueva = new Categoria(null, request.nombre(), request.descripcion());
        Categoria guardada = categoriaService.save(nueva);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapearAResponse(guardada));
    }

    /**
     * Actualiza los datos de una categoría existente.
     *
     * @param id      identificador de la categoría a modificar
     * @param request payload con los nuevos datos validados
     * @return respuesta HTTP 200 con la categoría actualizada
     */
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponse> actualizar(@PathVariable Long id, @Valid @RequestBody CategoriaRequest request) {
        Categoria actualizada = new Categoria(null, request.nombre(), request.descripcion());
        Categoria guardada = categoriaService.update(id, actualizada);
        return ResponseEntity.ok(mapearAResponse(guardada));
    }

    /**
     * Elimina una categoría del sistema.
     *
     * @param id identificador de la categoría a eliminar
     * @return respuesta HTTP 204 sin contenido
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        categoriaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Método auxiliar para convertir una entidad de dominio a DTO.
     *
     * @param categoria entidad Categoria
     * @return DTO CategoriaResponse
     */
    private CategoriaResponse mapearAResponse(Categoria categoria) {
        return new CategoriaResponse(categoria.getId(), categoria.getNombre(), categoria.getDescripcion());
    }
}
