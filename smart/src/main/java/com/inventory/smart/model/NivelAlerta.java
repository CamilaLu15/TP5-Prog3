package com.inventory.smart.model;

/**
 * Representa los distintos niveles de alerta de stock para un producto.
 * * @author Grupo Integrador
 * @since 1.0
 */

public enum NivelAlerta {

    /** Stock superior o igual al nivel mínimo. */
    NORMAL,
    
    /** Stock por debajo del nivel mínimo, pero por encima del nivel crítico. */
    BAJO,
    
    /** Stock por debajo del nivel crítico. */
    CRITICO
}
