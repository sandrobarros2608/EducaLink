package com.company.educalink.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Generic service interface for basic CRUD operations.
 *
 * @param <T>  the type of the entity
 * @param <ID> the type of the entity's identifier
 *
 * @author Sandro Barros
 * @Since 1.0.0
 */
public interface GenericService<T, ID> {

    /**
     * Saves a new entity.
     *
     * @param entity the entity to be saved
     * @return the saved entity
     */
    T save(T entity);

    /**
     * Finds an entity by its ID.
     *
     * @param id the ID of the entity
     * @return the found entity
     */
    T findById(ID id);

    /**
     * Retrieves all entities.
     *
     * @return a list of all entities
     */
    List<T> getAll();

    /**
     * Retrieves entities in a paginated format.
     *
     * @param pageable pagination and sorting information
     * @return a page of entities
     */
    Page<T> getAllPaginated(Pageable pageable);

    /**
     * Updates an existing entity.
     *
     * @param id     the ID of the entity to update
     * @param entity the updated entity data
     * @return the updated entity
     */
    T update(ID id, T entity);

    /**
     * Deletes an entity by its ID.
     *
     * @param id the ID of the entity to delete
     */
    void deleteById(ID id);
}
