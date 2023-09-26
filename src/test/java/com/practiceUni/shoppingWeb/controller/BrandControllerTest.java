//package com.practiceUni.shoppingWeb.controller;
//
//import com.practiceUni.shoppingWeb.domain.Brand;
//import com.practiceUni.shoppingWeb.service.impl.BrandServiceImpl;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@ExtendWith(MockitoExtension.class)
//class BrandControllerTest {
//
//  @Mock private BrandServiceImpl brandServiceImpl;
//
//  private BrandController brandController;
//
//  @BeforeEach
//  void setUp() {
//    brandController = new BrandController(brandServiceImpl);
//  }
//
//  private Brand getTestBrand(){
//    return new Brand(1, "name", "email", 1);
//  }
//
//  @Test
//  void shouldCreateBrand() {
//    Brand brand = getTestBrand();
//
//    Mockito.when(brandServiceImpl.create(brand)).thenReturn(brand);
//    assertNotNull(brandController.createBrand(brand));
//
//    Mockito.when(brandServiceImpl.findBrandById(brand.getId())).thenReturn(brand);
//    Brand testBrand = brandController.findBrandById(brand.getId());
//    assertNotNull(testBrand);
//    assertEquals(testBrand,brand);
//
//  }
//
//  @Test
//  void shouldUpdateBrand() {
//    Brand brand = getTestBrand();
//
//    Mockito.when(brandServiceImpl.create(brand)).thenReturn(brand);
//    assertNotNull(brandController.createBrand(brand));
//
//    brand.setEmail("new email");
//
//    Mockito.when(brandServiceImpl.update(brand)).thenReturn(brand);
//    Brand updatedBrand = brandController.updateBrand(brand);
//    assertNotNull(updatedBrand);
//
//    Mockito.when(brandServiceImpl.findBrandById(updatedBrand.getId())).thenReturn(updatedBrand);
//    Brand foundBrand = brandController.findBrandById(updatedBrand.getId());
//    assertNotNull(foundBrand);
//    assertEquals(foundBrand,updatedBrand);
//
//  }
//
//  @Test
//  void shouldCreateBrandIfNotFound(){
//    Brand brand = new Brand("name","email", 1);
//
//    Mockito.when(brandServiceImpl.findBrandById(brand.getId())).thenReturn(null);
//    assertNull(brandController.findBrandById(brand.getId()));
//
//    Mockito.when(brandServiceImpl.create(brand)).thenReturn(brand);
//    brand.setId(1);
//    assertNotNull(brandController.createBrand(brand));
//
//    Mockito.when(brandServiceImpl.findBrandById(brand.getId())).thenReturn(brand);
//    Brand testBrand = brandController.findBrandById(brand.getId());
//    assertNotNull(testBrand);
//    assertEquals(testBrand,brand);
//
//  }
//
//  @Test
//  void shouldDeleteBrandById() {
//Brand brand = getTestBrand();
//
//Mockito.when(brandServiceImpl.create(brand)).thenReturn(brand);
//assertNotNull(brandController.createBrand(brand));
//
//Mockito.when(brandServiceImpl.deleteById(brand.getId())).thenReturn(true);
//assertTrue(brandController.deleteBrandById(brand.getId()));
//
//  }
//
//  @Test
//  void findBrandById() {
//    Brand brand = getTestBrand();
//
//    Mockito.when(brandServiceImpl.create(brand)).thenReturn(brand);
//    assertNotNull(brandController.createBrand(brand));
//
//    Mockito.when(brandServiceImpl.findBrandById(brand.getId())).thenReturn(brand);
//    Brand foundBrand = brandController.findBrandById(brand.getId());
//    assertNotNull(foundBrand);
//    assertEquals(foundBrand,brand);
//
//  }
//
//  @Test
//  void findBrandByName() {
//    Brand brand = getTestBrand();
//
//    Mockito.when(brandServiceImpl.create(brand)).thenReturn(brand);
//    assertNotNull(brandController.createBrand(brand));
//
//    Mockito.when(brandServiceImpl.findBrandByName(brand.getName())).thenReturn(brand);
//    Brand foundBrand = brandController.findBrandByName(brand.getName());
//    assertNotNull(foundBrand);
//    assertEquals(foundBrand,brand);
//
//  }
//
//  @Test
//  void getAllBrands() {
//    Brand brand = getTestBrand();
//Brand brand1 = getTestBrand();
//
//Mockito.when(brandServiceImpl.create(brand)).thenReturn(brand);
//assertNotNull(brandController.createBrand(brand));
//Mockito.when(brandServiceImpl.create(brand1)).thenReturn(brand1);
//assertNotNull(brandController.createBrand(brand1));
//
//List<Brand> brandList = new ArrayList<>();
//brandList.add(brand);
//brandList.add(brand1);
//
//Mockito.when(brandServiceImpl.getAllBrands()).thenReturn(brandList);
//List<Brand> testList = brandController.getAllBrands();
//assertNotNull(testList);
//assertEquals(testList,brandList);
//
//  }
//}
