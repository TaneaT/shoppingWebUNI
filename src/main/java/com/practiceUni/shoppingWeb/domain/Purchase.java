package com.practiceUni.shoppingWeb.domain;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

public class Purchase {

  private Integer purchaseId;

  private Timestamp purchaseDateTime = Timestamp.valueOf(LocalDateTime.now());

  private Integer userId;

  private Integer productId;

  private Integer brandId;

  private String userAddress;

  public Purchase(Integer purchaseId, Timestamp purchaseDateTime, Integer userId, String userAddress, Integer productId, Integer brandId) {
    this.purchaseId = purchaseId;
    this.purchaseDateTime = purchaseDateTime;
    this.userId = userId;
    this.productId = productId;
    this.brandId = brandId;
    this.userAddress = userAddress;
  }

  public Purchase(Timestamp purchaseDateTime, Integer userId, Integer productId, Integer brandId) {
    this.purchaseDateTime = purchaseDateTime;
    this.userId = userId;
    this.productId = productId;
    this.brandId = brandId;
  }

  public Purchase(Integer userId, Integer productId, Integer brandId) {
    this.userId = userId;
    this.productId = productId;
    this.brandId = brandId;
  }

  public Purchase() {}

  public Integer getPurchaseId() {
    return purchaseId;
  }

  public void setPurchaseId(Integer purchaseId) {
    this.purchaseId = purchaseId;
  }

  public Timestamp getPurchaseDateTime() {
    return purchaseDateTime;
  }

  public void setPurchaseDateTime(Timestamp purchaseDateTime) {
    this.purchaseDateTime = purchaseDateTime;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public Integer getProductId() {
    return productId;
  }

  public void setProductId(Integer productId) {
    this.productId = productId;
  }

  public Integer getBrandId() {
    return brandId;
  }

  public void setBrandId(Integer brandId) {
    this.brandId = brandId;
  }

  public String getUserAdress() {
    return userAddress;
  }

  public void setUserAdress(String userAdress) {
    this.userAddress = userAdress;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Purchase purchase = (Purchase) o;
    return Objects.equals(purchaseId, purchase.purchaseId)
        && Objects.equals(purchaseDateTime, purchase.purchaseDateTime)
        && Objects.equals(userId, purchase.userId)
        && Objects.equals(productId, purchase.productId)
        && Objects.equals(brandId, purchase.brandId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(purchaseId, purchaseDateTime, userId, productId, brandId);
  }
}
