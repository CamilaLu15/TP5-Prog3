package com.inventory.smart.model;

import java.time.LocalDateTime;

/**
 * Registra una operación de entrada o salida de stock para mantener la trazabilidad.
 * * @author Grupo Integrador
 * @since 1.0
 */

public class MovimientoInventario {
    private Long id;
    private Long productoId;
    private TipoMovimiento tipo;
    private int cantidad;
    private int stockResultante;
    private String motivo;
    private LocalDateTime fecha;

    /**
     * Constructor completo para registrar un nuevo movimiento.
     * * @param id              identificador único del movimiento
     * @param productoId      identificador del producto afectado
     * @param tipo            tipo de movimiento (ENTRADA o SALIDA)
     * @param cantidad        unidades involucradas en la operación
     * @param stockResultante stock del producto tras la operación
     * @param motivo          justificación del movimiento
     * @param fecha           fecha y hora exacta de la transacción
     */
    public MovimientoInventario(Long id, Long productoId, TipoMovimiento tipo, int cantidad, int stockResultante, String motivo, LocalDateTime fecha) {
        this.id = id;
        this.productoId = productoId;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.stockResultante = stockResultante;
        this.motivo = motivo;
        this.fecha = fecha;
    }

    /**
     * Obtiene el identificador del movimiento.
     * * @return el identificador único
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador del movimiento.
     * * @param id el nuevo identificador
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el identificador del producto afectado.
     * * @return el ID del producto
     */
    public Long getProductoId() {
        return productoId;
    }

    /**
     * Establece el identificador del producto afectado.
     * * @param productoId el ID a asignar
     */
    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    /**
     * Obtiene el tipo de movimiento.
     * * @return ENTRADA o SALIDA
     */
    public TipoMovimiento getTipo() {
        return tipo;
    }

    /**
     * Establece el tipo de movimiento.
     * * @param tipo el nuevo tipo
     */
    public void setTipo(TipoMovimiento tipo) {
        this.tipo = tipo;
    }

    /**
     * Obtiene la cantidad de unidades movidas.
     * * @return la cantidad implicada
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Establece la cantidad de unidades movidas.
     * * @param cantidad las unidades de la operación
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Obtiene el stock que quedó registrado luego de la operación.
     * * @return el stock resultante
     */
    public int getStockResultante() {
        return stockResultante;
    }

    /**
     * Establece el stock resultante de la operación.
     * * @param stockResultante el stock post-movimiento
     */
    public void setStockResultante(int stockResultante) {
        this.stockResultante = stockResultante;
    }

    /**
     * Obtiene el motivo de la operación.
     * * @return el texto justificativo
     */
    public String getMotivo() {
        return motivo;
    }

    /**
     * Establece el motivo de la operación.
     * * @param motivo la justificación del movimiento
     */
    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    /**
     * Obtiene la fecha y hora de la transacción.
     * * @return la fecha exacta
     */
    public LocalDateTime getFecha() {
        return fecha;
    }

    /**
     * Establece la fecha y hora de la transacción.
     * * @param fecha la fecha y hora a asignar
     */
    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
}
