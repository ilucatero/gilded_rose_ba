package com.gildedrose.core.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

public abstract class AbstractJpaDAO <T extends Serializable> implements Dao<T>{

    @PersistenceContext
    EntityManager entityManager;

    private Class<T> clazz;

    public final void setClazz(Class<T> clazzToSet) {
        this.clazz = clazzToSet;
    }

    public T findOne(long id) {
        return entityManager.find(clazz, id);
    }

    public List<T> findAll() {
        return entityManager.createQuery("FROM " + clazz.getName()).getResultList();
    }

    public List<T> findSome(List<Long> ids) {
        Query query = entityManager.createQuery("SELECT e FROM " + clazz.getName() + " e WHERE e.id IN (:ids)");
        query.setParameter("ids",ids);
        return query.getResultList();
    }

    public void create(T entity) {
        entityManager.persist(entity);
    }

    public T update(T entity) {
        return entityManager.merge(entity);
    }

    public void delete(T entity) {
        entityManager.remove(entity);
    }

    public void deleteById(long entityId) {
        T entity = findOne(entityId);
        delete(entity);
    }
}
