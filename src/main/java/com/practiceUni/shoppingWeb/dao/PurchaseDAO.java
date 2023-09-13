package com.practiceUni.shoppingWeb.dao;

import com.practiceUni.shoppingWeb.dao.DAO;
import com.practiceUni.shoppingWeb.domain.Purchase;

import java.util.List;

public interface PurchaseDAO {

  Purchase create(Purchase purchase);

  boolean deleteById(Integer id);

  Purchase findById(Integer id);

  List<Purchase> findAllById(Integer id);
}
