package com.inventory.smart.controller;

import com.inventory.smart.dto.CategoriaResponse;
import com.inventory.smart.dto.ProductoRequest;
import com.inventory.smart.dto.ProductoResponse;
import com.inventory.smart.model.Categoria;
import com.inventory.smart.model.Producto;
import com.inventory.smart.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controlador REST encargado de gestionar las peticiones HTTP relacionadas con los productos.
 * <p>Expone los endpoints para el CRUD, búsquedas y ordenamientos, respetando
 * la arquitectura en capas al devolver únicamente DTOs.</p>
 *
 * @author Grupo Integrador
 * @since 1.0
 */
@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;

    /**
     * Constructor para la inyección de dependencias.
     *
     * @param productoService servicio de lógica de negocio de productos
     */
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    /**
     * Lista todos los productos disponibles en el catálogo.
     *
     * @return respuesta HTTP 200 con la lista de productos
     */
    @GetMapping
    public ResponseEntity<List<ProductoResponse>> listarTodos() {
        List<ProductoResponse> respuestas = productoService.findAll().stream()
                .map(this::mapearAResponse)
                .toList();
        return ResponseEntity.ok(respuestas);
    }

    /**
     * Obtiene un producto específico por su identificador.
     *
     * @param id identificador único del producto
     * @return respuesta HTTP 200 con el producto encontrado
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponse> obtenerPorId(@PathVariable Long id) {
        Producto producto = productoService.findById(id);
        return ResponseEntity.ok(mapearAResponse(producto));
    }

    /**
     * Crea un nuevo producto en el sistema.
     *
     * @param request payload con los datos del producto validados
     * @return respuesta HTTP 201 con los datos del producto creado
     */
    @PostMapping
    public ResponseEntity<ProductoResponse> crear(@Valid @RequestBody ProductoRequest request) {
        Categoria cat = new Categoria(request.categoriaId(), null, null);
        Producto nuevo = new Producto(null, request.nombre(), request.descripcion(), request.precio(), request.stockInicial(), cat);
        
        Producto guardado = productoService.save(nuevo);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapearAResponse(guardado));
    }

    /**
     * Actualiza los datos de un producto existente.
     *
     * @param id      identificador del producto a modificar
     * @param request payload con los nuevos datos validados
     * @return respuesta HTTP 200 con el producto actualizado
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponse> actualizar(@PathVariable Long id, @Valid @RequestBody ProductoRequest request) {
        Categoria cat = new Categoria(request.categoriaId(), null, null);
        Producto actualizado = new Producto(null, request.nombre(), request.descripcion(), request.precio(), request.stockInicial(), cat);
        
        Producto guardado = productoService.update(id, actualizado);
        return ResponseEntity.ok(mapearAResponse(guardado));
    }

    /**
     * Elimina un producto del catálogo.
     *
     * @param id identificador del producto a eliminar
     * @return respuesta HTTP 204 sin contenido
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        productoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Busca productos cuyo nombre contenga el texto proporcionado.
     *
     * @param q texto de búsqueda
     * @return respuesta HTTP 200 con la lista de coincidencias
     */
    @GetMapping("/buscar")
    public ResponseEntity<List<ProductoResponse>> buscarPorNombre(@RequestParam String q) {
        List<ProductoResponse> respuestas = productoService.buscarPorNombre(q).stream()
                .map(this::mapearAResponse)
                .toList();
        return ResponseEntity.ok(respuestas);
    }

    /**
     * Lista los productos ordenados dinámicamente.
     *
     * @param campo atributo de ordenamiento (nombre, precio, stock)
     * @param orden dirección (asc o desc)
     * @return respuesta HTTP 200 con la lista ordenada
     */
    @GetMapping("/ordenados")
    public ResponseEntity<List<ProductoResponse>> listarOrdenados(
            @RequestParam(defaultValue = "nombre") String campo,
            @RequestParam(defaultValue = "asc") String orden) {
        
        List<ProductoResponse> respuestas = productoService.listarOrdenados(campo, orden).stream()
                .map(this::mapearAResponse)
                .toList();
        return ResponseEntity.ok(respuestas);
    }


    /**
     * Método auxiliar para convertir una entidad de dominio a DTO.
     *
     * @param p entidad Producto
     * @return DTO ProductoResponse
     */
    private ProductoResponse mapearAResponse(Producto p) {
        CategoriaResponse catDto = new CategoriaResponse(p.getCategoria().getId(), p.getCategoria().getNombre(), p.getCategoria().getDescripcion());
        return new ProductoResponse(p.getId(), p.getNombre(), p.getDescripcion(), p.getPrecio(), p.getStock(), catDto);
    }
}
