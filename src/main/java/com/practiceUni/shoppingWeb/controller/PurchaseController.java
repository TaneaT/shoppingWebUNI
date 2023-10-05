package com.practiceUni.shoppingWeb.controller;

import com.practiceUni.shoppingWeb.domain.Purchase;
import com.practiceUni.shoppingWeb.domain.User;
import com.practiceUni.shoppingWeb.service.PurchaseService;
import com.practiceUni.shoppingWeb.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api/purchase")
public class PurchaseController {

  private final PurchaseService purchaseService;


  public PurchaseController(PurchaseService purchaseService ) {
    this.purchaseService = purchaseService;

  }

  @PostMapping("/create")
  public Purchase createPurchase(@RequestBody Purchase purchase) {
    return purchaseService.createPurchase(purchase);
  }

  @DeleteMapping("/delete/{id}")
  public boolean deletePurchaseById(@PathVariable Integer id) {
    return purchaseService.deletePurchaseById(id);
  }

  @GetMapping("/find/id/{id}")
  public Purchase findPurchaseById(@PathVariable Integer id) {
    return purchaseService.findPurchaseById(id);
  }

  @GetMapping("/find/all/id/{id}")
  public List<Purchase> findAllPurchaseById(@PathVariable Integer id) {
    return purchaseService.findAllPurchaseByUserId(id);
  }
}
