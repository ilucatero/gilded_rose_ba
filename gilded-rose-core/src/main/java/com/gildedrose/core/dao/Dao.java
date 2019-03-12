package com.gildedrose.core.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {

    /**
     * Get an item with the giving id
     * @param id
     * @return an optional object that may containing the result
     */
    Optional<T> get(long id);

    /**
     * Get all items in the base
     * @return
     */
    List<T> getAll();

    /**
     * Save in the base the giving item
     * @param t
     */
    void save(T t);

    /**
     * Update in the base the giving item
     * @param t
     */
    void update(T t, String[] params);

    /**
     * Update in the base the giving item
     * @param t
     */
    void delete(T t);
}