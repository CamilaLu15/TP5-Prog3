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

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public ResponseEntity<List<ProductoResponse>> listarTodos() {
        List<ProductoResponse> respuestas = productoService.findAll().stream()
                .map(this::mapearAResponse)
                .toList();
        return ResponseEntity.ok(respuestas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponse> obtenerPorId(@PathVariable Long id) {
        Producto producto = productoService.findById(id);
        return ResponseEntity.ok(mapearAResponse(producto));
    }

    @PostMapping
    public ResponseEntity<ProductoResponse> crear(@Valid @RequestBody ProductoRequest request) {
        Categoria cat = new Categoria(request.categoriaId(), null, null);
        Producto nuevo = new Producto(null, request.nombre(), request.descripcion(), request.precio(), request.stockInicial(), cat);
        
        Producto guardado = productoService.save(nuevo);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapearAResponse(guardado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponse> actualizar(@PathVariable Long id, @Valid @RequestBody ProductoRequest request) {
        Categoria cat = new Categoria(request.categoriaId(), null, null);
        Producto actualizado = new Producto(null, request.nombre(), request.descripcion(), request.precio(), request.stockInicial(), cat);
        
        Producto guardado = productoService.update(id, actualizado);
        return ResponseEntity.ok(mapearAResponse(guardado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        productoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<ProductoResponse>> buscarPorNombre(@RequestParam String q) {
        List<ProductoResponse> respuestas = productoService.buscarPorNombre(q).stream()
                .map(this::mapearAResponse)
                .toList();
        return ResponseEntity.ok(respuestas);
    }

    @GetMapping("/ordenados")
    public ResponseEntity<List<ProductoResponse>> listarOrdenados(
            @RequestParam(defaultValue = "nombre") String campo,
            @RequestParam(defaultValue = "asc") String orden) {
        
        List<ProductoResponse> respuestas = productoService.listarOrdenados(campo, orden).stream()
                .map(this::mapearAResponse)
                .toList();
        return ResponseEntity.ok(respuestas);
    }


    private ProductoResponse mapearAResponse(Producto p) {
        CategoriaResponse catDto = new CategoriaResponse(p.getCategoria().getId(), p.getCategoria().getNombre(), p.getCategoria().getDescripcion());
        return new ProductoResponse(p.getId(), p.getNombre(), p.getDescripcion(), p.getPrecio(), p.getStock(), catDto);
    }
}
