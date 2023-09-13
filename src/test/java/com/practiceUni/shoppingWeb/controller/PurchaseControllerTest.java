package com.practiceUni.shoppingWeb.controller;

import com.practiceUni.shoppingWeb.domain.Purchase;
import com.practiceUni.shoppingWeb.service.impl.PurchaseServiceImpl;
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
class PurchaseControllerTest {

    @Mock
    private PurchaseServiceImpl purchaseServiceImpl;

    private PurchaseController purchaseController;

    @BeforeEach
    void setUp() {
        purchaseController = new PurchaseController(purchaseServiceImpl);
      }

      private Purchase getTestPurchase(){
        return new Purchase(1, Timestamp.valueOf(LocalDateTime.now()),1,"address",1,1);
      }

    @Test
    void createPurchase() {
        Purchase  purchase = getTestPurchase();

        Mockito.when(purchaseServiceImpl.createPurchase(purchase)).thenReturn(purchase);
        assertNotNull(purchaseController.createPurchase(purchase));

        Mockito.when(purchaseServiceImpl.findPurchaseById(purchase.getPurchaseId())).thenReturn(purchase);
        Purchase testPurchase = purchaseController.findPurchaseById(purchase.getPurchaseId());
        assertNotNull(testPurchase);
        assertEquals(testPurchase,purchase);
      }

    @Test
    void deletePurchaseById() {
        Purchase purchase = getTestPurchase();
        Mockito.when(purchaseServiceImpl.createPurchase(purchase)).thenReturn(purchase);
        assertNotNull(purchaseController.createPurchase(purchase));

        Mockito.when(purchaseServiceImpl.deletePurchaseById(purchase.getPurchaseId())).thenReturn(true);
        assertTrue(purchaseController.deletePurchaseById(purchase.getPurchaseId()));
      }

    @Test
    void findPurchaseById() {
        Purchase purchase =getTestPurchase();
        Mockito.when(purchaseServiceImpl.createPurchase(purchase)).thenReturn(purchase);
        assertNotNull(purchaseController.createPurchase(purchase));

        Mockito.when(purchaseServiceImpl.findPurchaseById(purchase.getPurchaseId())).thenReturn(purchase);
        Purchase foundPurchase = purchaseController.findPurchaseById(purchase.getPurchaseId());
        assertNotNull(foundPurchase);
        assertEquals(foundPurchase,purchase);
      }

    @Test
    void findAllPurchaseById() {
        Purchase purchase = getTestPurchase();
        Purchase purchase1 = getTestPurchase();

        Mockito.when(purchaseServiceImpl.createPurchase(purchase)).thenReturn(purchase);
        assertNotNull(purchaseController.createPurchase(purchase));
        Mockito.when(purchaseServiceImpl.createPurchase(purchase1)).thenReturn(purchase1);
        assertNotNull(purchaseController.createPurchase(purchase1));

        List<Purchase> purchaseList = new ArrayList<>();
        purchaseList.add(purchase);
        purchaseList.add(purchase1);

        Mockito.when(purchaseServiceImpl.findAllPurchaseByUserId(purchase.getUserId())).thenReturn(purchaseList);
        List<Purchase> allThePurchases = purchaseController.findAllPurchaseById(purchase.getUserId());
        assertNotNull(allThePurchases);
        assertEquals(allThePurchases,purchaseList);

      }
}