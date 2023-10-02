package com.practiceUni.shoppingWeb.domain;

import java.sql.Blob;
import java.util.Objects;

public class Product {

  private Integer id;

  private String name;

  private String category;

  private String size;

  private String color;

  private Integer quantity;

  private Blob image;

  private String imageBase64;

  public String getImageBase64() {
    return imageBase64;
  }

  public void setImageBase64(String imageBase64) {
    this.imageBase64 = imageBase64;
  }


  public Product(Integer id, String name, String category, String size, String color, Integer quantity, String imageBase64) {
    this.id = id;
    this.name = name;
    this.category = category;
    this.size = size;
    this.color = color;
    this.quantity = quantity;
    this.imageBase64 = imageBase64;
  }

  public Product(
      Integer id, String name, String category, String size, String color, Integer quantity) {
    this.id = id;
    this.name = name;
    this.category = category;
    this.size = size;
    this.color = color;
    this.quantity = quantity;
  }

  public Product(Integer id, String name, String category, String size, String color, Integer quantity, Blob image) {
    this.id = id;
    this.name = name;
    this.category = category;
    this.size = size;
    this.color = color;
    this.quantity = quantity;
    this.image = image;
  }

  public Product(String name, String category, String size, String color, Integer quantity) {
    this.name = name;
    this.category = category;
    this.size = size;
    this.color = color;
    this.quantity = quantity;
  }

  public Product() {}

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getSize() {
    return size;
  }

  public void setSize(String size) {
    this.size = size;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public Blob getImage() {
    return image;
  }

  public void setImage(Blob image) {
    this.image = image;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Product product = (Product) o;
    return Objects.equals(id, product.id)
        && Objects.equals(name, product.name)
        && Objects.equals(category, product.category)
        && Objects.equals(size, product.size)
        && Objects.equals(color, product.color)
        && Objects.equals(quantity, product.quantity);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, category, size, color, quantity);
  }
}
