package com.practiceUni.shoppingWeb.service.impl;

import com.practiceUni.shoppingWeb.dao.PurchaseDAO;
import com.practiceUni.shoppingWeb.domain.Purchase;
import com.practiceUni.shoppingWeb.service.PurchaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseServiceImpl implements PurchaseService {

  private static final Logger LOGGER = LoggerFactory.getLogger(PurchaseServiceImpl.class);

  private final PurchaseDAO purchaseDAO;

  public PurchaseServiceImpl(PurchaseDAO purchaseDAO) {
    this.purchaseDAO = purchaseDAO;
  }

  @Override
  public Purchase createPurchase(Purchase purchase) {
    return purchaseDAO.create(purchase);
  }

  @Override
  public boolean deletePurchaseById(Integer id) {
    if (id == null) {
      LOGGER.error("Failed to delete the purchase due to null id");
      return false;
    } else {
      return purchaseDAO.deleteById(id);
    }
  }

  @Override
  public Purchase findPurchaseById(Integer id) {
    Purchase purchase = purchaseDAO.findById(id);

    if (purchase == null) {
      LOGGER.error("Failed to find a purchase due to wrong id: " + id);
      return null;
    } else {
      return purchase;
    }
  }

  @Override
  public List<Purchase> findAllPurchaseByUserId(Integer id) {
    List<Purchase> purchases = purchaseDAO.findAllById(id);

    if (purchases == null) {
      LOGGER.error("Failed to find any purchases made by user with id: " + id);
      return null;
    } else {
      return purchases;
    }
  }
}
