package com.inventory.smart.repository;

import com.inventory.smart.exception.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Implementación base abstracta en memoria para los repositorios.
 * <p>Utiliza {@link java.util.concurrent.ConcurrentHashMap} para asegurar accesos
 * concurrentes sin bloqueos globales y evitar ConcurrentModificationException.</p>
 *
 * @param <T>  tipo de la entidad
 * @param <ID> tipo del identificador
 * @author Grupo Integrador
 * @since 1.0
 */

public abstract class GenericInMemoryRepository<T, ID> implements IGenericRepository<T, ID> {
    protected final ConcurrentHashMap<ID, T> dataStore = new ConcurrentHashMap<>();
    protected final AtomicLong idGenerator = new AtomicLong(0);

    @Override
    public List<T> findAll() {
        return new ArrayList<>(dataStore.values());
    }

    @Override
    public Optional<T> findById(ID id) {
        return Optional.ofNullable(dataStore.get(id));
    }

    @Override
    public T save(T entity) {
        ID id = getEntityId(entity);
        if (id == null) {
            // Asumimos que los IDs serán Long para este sistema en particular
            @SuppressWarnings("unchecked")
            ID newId = (ID) Long.valueOf(idGenerator.incrementAndGet());
            setEntityId(entity, newId);
            id = newId;
        }
        dataStore.put(id, entity);
        return entity;
    }

    @Override
    public void deleteById(ID id) {
        if (dataStore.remove(id) == null) {
            throw new ResourceNotFoundException("No se encontró la entidad con ID: " + id);
        }
    }

    @Override
    public boolean existsById(ID id) {
        return dataStore.containsKey(id);
    }

    @Override
    public long count() {
        return dataStore.size();
    }

    /**
     * Obtiene el identificador actual de la entidad.
     *
     * @param entity la entidad a inspeccionar
     * @return el identificador, o null si es una entidad nueva
     */
    protected abstract ID getEntityId(T entity);

    /**
     * Establece el identificador autogenerado en la entidad.
     *
     * @param entity la entidad nueva
     * @param id     el identificador generado
     */
    protected abstract void setEntityId(T entity, ID id);
}
