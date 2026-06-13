package com.inventory.smart.model;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Representa un producto físico dentro del depósito inteligente.
 * * <p>Esta clase utiliza {@link java.util.concurrent.atomic.AtomicInteger} para
 * garantizar que las modificaciones al stock sean thread-safe ante peticiones
 * concurrentes, cumpliendo con la regla de atomicidad del negocio.</p>
 * * @author Grupo Integrador
 * @since 1.0
 */

public class Producto {
    private Long id;
    private String nombre;
    private String descripcion;
    private double precio;
    private Categoria categoria; // Composición sobre herencia
    private final AtomicInteger stock;

    /**
     * Constructor para inicializar un producto con su stock base.
     * * @param id           identificador único del producto
     * @param nombre       nombre del producto
     * @param descripcion  detalles del producto
     * @param precio       precio unitario
     * @param stockInicial cantidad de unidades iniciales (debe ser mayor o igual a 0)
     * @param categoria    categoría a la que pertenece el producto
     */
    public Producto(Long id, String nombre, String descripcion, double precio, int stockInicial, Categoria categoria) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = new AtomicInteger(Math.max(0, stockInicial));
        this.categoria = categoria;
    }

    /**
     * Obtiene el identificador del producto.
     * * @return el identificador único
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador del producto.
     * * @param id el nuevo identificador
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del producto.
     * * @return el nombre actual
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del producto.
     * * @param nombre el nuevo nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la descripción del producto.
     * * @return la descripción actual
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripción del producto.
     * * @param descripcion la nueva descripción
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene el precio unitario del producto.
     * * @return el precio actual
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Establece el precio unitario del producto.
     * * @param precio el nuevo precio
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * Obtiene la categoría a la que pertenece el producto.
     * * @return la categoría asignada
     */
    public Categoria getCategoria() {
        return categoria;
    }

    /**
     * Establece o cambia la categoría del producto.
     * * @param categoria la nueva categoría
     */
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    /**
     * Obtiene la cantidad actual de stock disponible de forma segura.
     * * @return unidades en stock
     */
    public int getStock() {
        return stock.get();
    }

    /**
     * Incrementa el stock actual de forma atómica y segura para hilos.
     * * @param cantidad unidades a sumar al stock actual
     * @return el nuevo valor del stock tras el incremento
     */
    public int incrementarStock(int cantidad) {
        return stock.addAndGet(cantidad);
    }

    /**
     * Decrementa el stock actual de forma atómica y segura para hilos.
     * * @param cantidad unidades a restar al stock actual
     * @return el nuevo valor del stock tras el decremento
     */
    public int decrementarStock(int cantidad) {
        return stock.addAndGet(-cantidad);
    }
}
