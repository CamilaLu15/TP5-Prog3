package com.inventory.smart.model;

/**
 * Representa una categoría que agrupa productos similares en el inventario.
 * * @author Grupo Integrador
 * @since 1.0
 */

public class Categoria {

    private Long id;
    private String nombre;
    private String descripcion;

    /**
     * Constructor por defecto para inicializar una categoría vacía.
     */
    public Categoria() {
    }

    /**
     * Constructor completo para crear una nueva categoría.
     * * @param id          identificador único de la categoría
     * @param nombre      nombre descriptivo de la categoría
     * @param descripcion detalles adicionales sobre la categoría
    */
   public Categoria(Long id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    /**
     * Obtiene el identificador de la categoría.
     * * @return el identificador único
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador de la categoría.
     * * @param id el identificador a asignar
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre de la categoría.
     * * @return el nombre actual
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre de la categoría.
     * * @param nombre el nuevo nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la descripción de la categoría.
     * * @return la descripción actual
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripción de la categoría.
     * * @param descripcion la nueva descripción
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
