package com.practiceUni.shoppingWeb.service;

import com.practiceUni.shoppingWeb.dao.PurchaseDAO;
import com.practiceUni.shoppingWeb.domain.Purchase;

import java.util.List;

public interface PurchaseService {

  Purchase createPurchase(Purchase purchase);

  boolean deletePurchaseById(Integer id);

  Purchase findPurchaseById(Integer id);

  List<Purchase> findAllPurchaseByUserId(Integer id);
}
