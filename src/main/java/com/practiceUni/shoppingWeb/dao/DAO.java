package com.practiceUni.shoppingWeb.dao;

public interface DAO<T> {

    T create(T t);

    T update (T t);

    boolean deleteById(Integer id);

    T findById (Integer id);








}
