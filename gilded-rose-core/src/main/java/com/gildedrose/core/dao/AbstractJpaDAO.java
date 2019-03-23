package com.gildedrose.core.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
        return entityManager.createQuery("from " + clazz.getName()).getResultList();
    }

    public List<T> findSome(List<Long> ids) {
        return entityManager.createQuery("from " + clazz.getName() + " where id in (" + ids + ")").getResultList();
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
