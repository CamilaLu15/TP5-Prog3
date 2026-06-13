package com.inventory.smart.repository;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz genérica para repositorios que define las operaciones CRUD básicas.
 *
 * @param <T>  tipo de la entidad gestionada
 * @param <ID> tipo del identificador de la entidad
 * @author Grupo Integrador
 * @since 1.0
 */

public interface IGenericRepository<T, ID> {
    /**
     * Recupera todas las entidades.
     *
     * @return una lista con todas las entidades
     */
    List<T> findAll();

    /**
     * Recupera una entidad por su identificador.
     *
     * @param id identificador de la entidad
     * @return un Optional que contiene la entidad si se encuentra, o vacío si no
     */
    Optional<T> findById(ID id);

    /**
     * Guarda una entidad. Si la entidad no tiene ID asignada, le asigna uno nuevo.
     * Si ya tiene ID, actualiza la entrada existente.
     *
     * @param entity la entidad a guardar
     * @return la entidad guardada con su estado final
     */
    T save(T entity);

    /**
     * Elimina una entidad por su identificador.
     *
     * @param id identificador de la entidad a eliminar
     * @throws com.inventory.smart.exception.ResourceNotFoundException si la entidad no existe
     */
    void deleteById(ID id);

    /**
     * Verifica si existe una entidad con el identificador dado.
     *
     * @param id identificador a verificar
     * @return true si existe, false en caso contrario
     */
    boolean existsById(ID id);

    /**
     * Devuelve la cantidad total de entidades almacenadas.
     *
     * @return el número de entidades
     */
    long count();
}
