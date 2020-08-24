package by.it.academy.repository;

import java.io.Serializable;

public interface UserDao<T> {

    void create(T t);
    void update(T t);
    T read(Class clazz, Serializable id);
    void delete(T t);
    T find(String userId);
}
