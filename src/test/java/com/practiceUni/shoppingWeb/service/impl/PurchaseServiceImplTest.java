package com.practiceUni.shoppingWeb.service.impl;

import com.practiceUni.shoppingWeb.dao.impl.PurchaseDAOImpl;
import com.practiceUni.shoppingWeb.domain.Purchase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class PurchaseServiceImplTest {

    @Mock
    private PurchaseDAOImpl purchaseDAOImpl;

    private PurchaseServiceImpl purchaseServiceImpl;

    @BeforeEach
    void setUp() {
        purchaseServiceImpl = new PurchaseServiceImpl(purchaseDAOImpl);
      }

      private Purchase getTestPurchase(){
        return new Purchase(1, Timestamp.valueOf(LocalDateTime.now()),1,"address",1,1);
      }

    @Test
    void createPurchase() {
        Purchase purchase = getTestPurchase();

        Mockito.when(purchaseDAOImpl.create(purchase)).thenReturn(purchase);
        assertNotNull(purchaseServiceImpl.createPurchase(purchase));

        Mockito.when(purchaseDAOImpl.findById(purchase.getUserId())).thenReturn(purchase);
        Purchase testPurchase = purchaseServiceImpl.findPurchaseById(purchase.getPurchaseId());
        assertNotNull(testPurchase);
        assertEquals(testPurchase,purchase);
      }

    @Test
    void deletePurchaseById() {
        Purchase purchase = getTestPurchase();

        Mockito.when(purchaseDAOImpl.create(purchase)).thenReturn(purchase);
        assertNotNull(purchaseServiceImpl.createPurchase(purchase));

        Mockito.when(purchaseDAOImpl.deleteById(purchase.getProductId())).thenReturn(true);
        assertTrue(purchaseServiceImpl.deletePurchaseById(purchase.getProductId()));
      }

    @Test
    void findPurchaseById() {
        Purchase purchase = getTestPurchase();

        Mockito.when(purchaseDAOImpl.create(purchase)).thenReturn(purchase);
        assertNotNull(purchaseServiceImpl.createPurchase(purchase));

            Mockito.when(purchaseDAOImpl.findById(purchase.getProductId())).thenReturn(purchase);
            Purchase foundPurchase = purchaseServiceImpl.findPurchaseById(purchase.getProductId());
            assertNotNull(foundPurchase);
            assertEquals(foundPurchase,purchase);
      }

    @Test
    void findAllPurchaseByUserId() {
        Purchase purchase = getTestPurchase();
        Purchase purchase1 = getTestPurchase();

        Mockito.when(purchaseDAOImpl.create(purchase)).thenReturn(purchase);
        assertNotNull(purchaseServiceImpl.createPurchase(purchase));
        Mockito.when(purchaseDAOImpl.create(purchase1)).thenReturn(purchase1);
        assertNotNull(purchaseServiceImpl.createPurchase(purchase1));

        List<Purchase> purchaseList = new ArrayList<>();
        purchaseList.add(purchase);
        purchaseList.add(purchase1);

        Mockito.when(purchaseDAOImpl.findAllById(purchase.getUserId())).thenReturn(purchaseList);
        List<Purchase> foundPurchaseList = purchaseServiceImpl.findAllPurchaseByUserId(purchase.getUserId());
        assertNotNull(foundPurchaseList);
        assertEquals(foundPurchaseList,purchaseList);
      }
}