package com.gildedrose.core.dao;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface Dao<T> extends CrudRepository<T, Long> {

    /**
     * Get the items with the giving ids
     * @param id
     * @return an optional object that may containing the result
     */
    List<T> find(List<Long> id);

    /**
     * Get an item with the giving id
     * @param id
     * @return an optional object that may containing the result
     */
    Optional<T> get(Long id);

    /**
     * Get all items in the base
     * @return
     */
    List<T> getAll();

    /**
     * Update in the base the giving item
     * @param t
     */
    T update(T t, String[] params);

    /**
     * Update in the base the giving item
     * @param t
     */
    void delete(T t);
}