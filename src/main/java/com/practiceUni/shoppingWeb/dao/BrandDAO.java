package com.practiceUni.shoppingWeb.dao;

import com.practiceUni.shoppingWeb.dao.DAO;
import com.practiceUni.shoppingWeb.domain.Brand;

import java.util.List;

public interface BrandDAO extends DAO<Brand> {

  Brand create(Brand brand);

  Brand update(Brand brand);

  boolean deleteById(Integer id);

  Brand findById(Integer id);

  Brand findByName(String name);

  List<Brand> getAll();
}
