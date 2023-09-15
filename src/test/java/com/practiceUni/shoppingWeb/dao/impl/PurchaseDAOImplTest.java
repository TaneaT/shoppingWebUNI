package com.practiceUni.shoppingWeb.dao.impl;

import com.practiceUni.shoppingWeb.JdbcConnection;
import com.practiceUni.shoppingWeb.dao.PurchaseDAO;
import com.practiceUni.shoppingWeb.domain.Purchase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PurchaseDAOImplTest {

  @Autowired private PurchaseDAO purchaseDAO;

  private Purchase getTestPurchase() {
    return new Purchase(Timestamp.valueOf(LocalDateTime.now()), 80, 260, 90);
  }

  @AfterEach
  void tearDown() {
    String sql = "DELETE FROM purchase";
    String sql1 = "DELETE FROM user_purchase";



    try (Connection conn = JdbcConnection.getConnection();
         PreparedStatement purchase = conn.prepareStatement(sql);
         PreparedStatement userPurchase = conn.prepareStatement(sql1))
    {
userPurchase.executeUpdate();
      purchase.executeUpdate();

    } catch (SQLException e) {
      e.getStackTrace();
    }
  }

  @Test
  void create() {
    Purchase purchase = getTestPurchase();

    assertNotNull(purchaseDAO.create(purchase));

    Purchase testPurchase = purchaseDAO.findById(purchase.getPurchaseId());
    assertNotNull(testPurchase);
    assertEquals(testPurchase.getPurchaseId(), purchase.getPurchaseId());
  }

  @Test
  void deleteById() {
    Purchase purchase = getTestPurchase();
    assertNotNull(purchaseDAO.create(purchase));

    assertTrue(purchaseDAO.deleteById(purchase.getPurchaseId()));
  }

  @Test
  void findById() {
    Purchase purchase = getTestPurchase();
    assertNotNull(purchaseDAO.create(purchase));

    Purchase foundPurchase = purchaseDAO.findById(purchase.getPurchaseId());
    assertNotNull(foundPurchase);
    assertEquals(foundPurchase.getPurchaseId(), purchase.getPurchaseId());
  }

  @Test
  void findAllById() {
    Purchase purchase = getTestPurchase();
    Purchase purchase1 = getTestPurchase();

    assertNotNull(purchaseDAO.create(purchase));
    assertNotNull(purchaseDAO.create(purchase1));

    List<Purchase> purchases = new ArrayList<>();
    purchases.add(purchase);
    purchases.add(purchase1);

    List<Purchase> allPurchases = purchaseDAO.findAllById(purchase.getUserId());
    assertNotNull(allPurchases);
    assertEquals(allPurchases.size(), purchases.size());
  }
}
