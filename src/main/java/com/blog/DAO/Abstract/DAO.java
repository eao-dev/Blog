package com.blog.DAO.Abstract;

import com.sun.istack.NotNull;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public abstract class DAO<T, PK> implements IDAO<T, PK> {

    @PersistenceContext
    protected EntityManager em;

    protected T read(@NotNull Class<T> tClass, @NotNull Long privateKey) {
        return em.find(tClass, privateKey);
    }

    public void create(@NotNull T entity) {
        em.persist(entity);
    }

    public void update(@NotNull T entity) {
        em.merge(entity);
    }

    public void delete(@NotNull T entity) {
        em.remove(entity);
    }

}
