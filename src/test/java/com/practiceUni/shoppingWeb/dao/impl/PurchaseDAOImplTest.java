package com.practiceUni.shoppingWeb.dao.impl;

import com.practiceUni.shoppingWeb.JdbcConnection;
import com.practiceUni.shoppingWeb.dao.BrandDAO;
import com.practiceUni.shoppingWeb.dao.ProductDAO;
import com.practiceUni.shoppingWeb.dao.PurchaseDAO;
import com.practiceUni.shoppingWeb.dao.UserDAO;
import com.practiceUni.shoppingWeb.domain.Brand;
import com.practiceUni.shoppingWeb.domain.Product;
import com.practiceUni.shoppingWeb.domain.Purchase;
import com.practiceUni.shoppingWeb.domain.User;
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

  @Autowired private UserDAO userDAO;

  @Autowired private ProductDAO productDAO;

  @Autowired private BrandDAO brandDAO;

  @AfterEach
  void tearDown() {

    String sql1 = "DELETE FROM user_purchase";


    try (Connection conn = JdbcConnection.getConnection();
         PreparedStatement userPurchase = conn.prepareStatement(sql1))
    {
userPurchase.executeUpdate();



    } catch (SQLException e) {
      e.getStackTrace();
    }
  }

  @Test
  void create() {
    Product product = new Product("Name","category","size", "color",1);
    User user = new User("fname","lname","ulogin","password","uemail","adress");

    assertNotNull(productDAO.create(product));
    assertNotNull(userDAO.create(user));

    Brand brand = new Brand("name", "email", product.getId());
    assertNotNull(brandDAO.create(brand));

    Purchase purchase = new Purchase(Timestamp.valueOf(LocalDateTime.now()), user.getId(), product.getId(), brand.getId());
    assertNotNull(purchaseDAO.create(purchase));


    Purchase testPurchase = purchaseDAO.findById(purchase.getPurchaseId());
    assertNotNull(testPurchase);
    assertEquals(testPurchase.getPurchaseId(), purchase.getPurchaseId());

    assertTrue(purchaseDAO.deleteById(purchase.getPurchaseId()));
    assertTrue(userDAO.deleteById(user.getId()));
    assertTrue(productDAO.deleteById(product.getId()));
    assertTrue(brandDAO.deleteById(brand.getId()));
  }

  @Test
  void deleteById() {
    Product product = new Product("Name","category","size", "color",1);
    User user = new User("fname","lname","ulogin","password","uemail","adress");

    assertNotNull(productDAO.create(product));
    assertNotNull(userDAO.create(user));

    Brand brand = new Brand("name", "email", product.getId());
    assertNotNull(brandDAO.create(brand));

    Purchase purchase = new Purchase(Timestamp.valueOf(LocalDateTime.now()), user.getId(), product.getId(), brand.getId());
    assertNotNull(purchaseDAO.create(purchase));

    assertTrue(purchaseDAO.deleteById(purchase.getPurchaseId()));
    assertTrue(userDAO.deleteById(user.getId()));
    assertTrue(productDAO.deleteById(product.getId()));
    assertTrue(brandDAO.deleteById(brand.getId()));
  }

  @Test
  void findById() {
    Product product = new Product("Name","category","size", "color",1);
    User user = new User("fname","lname","ulogin","password","uemail","adress");

    assertNotNull(productDAO.create(product));
    assertNotNull(userDAO.create(user));

    Brand brand = new Brand("name", "email", product.getId());
    assertNotNull(brandDAO.create(brand));

    Purchase purchase = new Purchase(Timestamp.valueOf(LocalDateTime.now()), user.getId(), product.getId(), brand.getId());
    assertNotNull(purchaseDAO.create(purchase));

    Purchase foundPurchase = purchaseDAO.findById(purchase.getPurchaseId());
    assertNotNull(foundPurchase);
    assertEquals(foundPurchase.getPurchaseId(), purchase.getPurchaseId());

    assertTrue(purchaseDAO.deleteById(purchase.getPurchaseId()));
    assertTrue(userDAO.deleteById(user.getId()));
    assertTrue(productDAO.deleteById(product.getId()));
    assertTrue(brandDAO.deleteById(brand.getId()));

  }

  @Test
  void findAllById() {
    Product product = new Product("Name","category","size", "color",1);
    User user = new User("fname","lname","ulogin","password","uemail","adress");

    assertNotNull(productDAO.create(product));
    assertNotNull(userDAO.create(user));

    Brand brand = new Brand("name", "email", product.getId());
    assertNotNull(brandDAO.create(brand));

    Purchase purchase = new Purchase(Timestamp.valueOf(LocalDateTime.now()), user.getId(), product.getId(), brand.getId());
    assertNotNull(purchaseDAO.create(purchase));


    List<Purchase> purchases = new ArrayList<>();
    purchases.add(purchase);


    List<Purchase> allPurchases = purchaseDAO.findAllById(purchase.getUserId());
    assertNotNull(allPurchases);
    assertEquals(allPurchases.size(), purchases.size());

    assertTrue(purchaseDAO.deleteById(purchase.getPurchaseId()));
    assertTrue(userDAO.deleteById(user.getId()));
    assertTrue(productDAO.deleteById(product.getId()));
    assertTrue(brandDAO.deleteById(brand.getId()));

  }
}
