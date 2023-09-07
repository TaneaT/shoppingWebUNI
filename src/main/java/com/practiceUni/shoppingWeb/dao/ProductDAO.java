package com.practiceUni.shoppingWeb.dao;

import com.practiceUni.shoppingWeb.domain.Product;

public interface ProductDAO extends DAO<Product> {

  Product create(Product product);

  Product update(Product product);

  boolean deleteById(Integer id);

  Product findById(Integer id);

  Product findByName(String name);
}
