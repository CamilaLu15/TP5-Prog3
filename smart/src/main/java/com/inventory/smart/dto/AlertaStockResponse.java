package com.inventory.smart.dto;

import com.inventory.smart.model.NivelAlerta;

/**
 * DTO que representa un producto cuyo stock se encuentra por debajo de los umbrales configurados.
 *
 * @param productoId  identificador del producto
 * @param nombre      nombre del producto
 * @param stockActual cantidad disponible actualmente
 * @param nivel       nivel de alerta (BAJO o CRITICO)
 * @author Grupo Integrador
 * @since 1.0
 */
public record AlertaStockResponse(Long productoId, String nombre, int stockActual, NivelAlerta nivel) {

}
