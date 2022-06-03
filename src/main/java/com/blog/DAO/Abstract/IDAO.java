package com.blog.DAO.Abstract;

import com.sun.istack.NotNull;

public interface IDAO<T, PK> {

    T read(@NotNull PK privateKey);

    void create(@NotNull T entity);

    void update(@NotNull T entity);

    void delete(@NotNull T entity);

}
