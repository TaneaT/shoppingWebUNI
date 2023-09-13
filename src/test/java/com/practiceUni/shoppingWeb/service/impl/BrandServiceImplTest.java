package com.practiceUni.shoppingWeb.service.impl;

import com.practiceUni.shoppingWeb.dao.impl.BrandDAOImpl;
import com.practiceUni.shoppingWeb.domain.Brand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class BrandServiceImplTest {
  @Mock private BrandDAOImpl brandDAOImpl;

  private BrandServiceImpl brandServiceImpl;

  private Brand getTestBrand() {
    return new Brand(1, "name", "email", 1);
  }

  @BeforeEach
  void setUp() {
    brandServiceImpl = new BrandServiceImpl(brandDAOImpl);
  }

  @Test
  void shouldCreateBrand() {
    Brand brand = getTestBrand();

    Mockito.when(brandDAOImpl.create(brand)).thenReturn(brand);
    assertNotNull(brandServiceImpl.create(brand));

    Mockito.when(brandDAOImpl.findById(brand.getId())).thenReturn(brand);
    Brand testBrand = brandServiceImpl.findBrandById(brand.getId());
    assertNotNull(testBrand);
    assertEquals(testBrand, brand);
  }

  @Test
  void shouldUpdateBrand() {
    Brand brand = getTestBrand();

    Mockito.when(brandDAOImpl.create(brand)).thenReturn(brand);
    assertNotNull(brandServiceImpl.create(brand));

    brand.setEmail("new email");

    Mockito.lenient().when(brandDAOImpl.update(brand)).thenReturn(brand);
    Brand updatedBrand = brandServiceImpl.update(brand);
    assertNotNull(updatedBrand);

    Mockito.when(brandDAOImpl.findById(updatedBrand.getId())).thenReturn(updatedBrand);
    Brand foundBrand = brandServiceImpl.findBrandById(updatedBrand.getId());
    assertNotNull(foundBrand);
    assertEquals(foundBrand, updatedBrand);
  }

  @Test
  void shouldCreateBrandIfNotFound(){
    Brand brand = new Brand("name", "email",1);

    Mockito.when(brandDAOImpl.findById(brand.getId())).thenReturn(null);
    assertNull(brandServiceImpl.findBrandById(brand.getId()));

    Mockito.when(brandDAOImpl.create(brand)).thenReturn(brand);
    brand.setId(1);
    assertNotNull(brandServiceImpl.create(brand));

    Mockito.when(brandDAOImpl.findById(brand.getId())).thenReturn(brand);
    Brand testBrand = brandServiceImpl.findBrandById(brand.getId());
    assertNotNull(testBrand);
    assertEquals(testBrand,brand);

  }


  @Test
  void shouldDeleteBrandById() {
    Brand brand = getTestBrand();
    Mockito.when(brandDAOImpl.create(brand)).thenReturn(brand);
    assertNotNull(brandServiceImpl.create(brand));

    Mockito.when(brandDAOImpl.deleteById(brand.getId())).thenReturn(true);
    assertTrue(brandServiceImpl.deleteById(brand.getId()));
  }

  @Test
  void shouldFindBrandById() {
    Brand brand = getTestBrand();

    Mockito.when(brandDAOImpl.create(brand)).thenReturn(brand);
    assertNotNull(brandServiceImpl.create(brand));

    Mockito.when(brandDAOImpl.findById(brand.getId())).thenReturn(brand);
    Brand foundBrand = brandServiceImpl.findBrandById(brand.getId());
    assertNotNull(foundBrand);
    assertEquals(foundBrand, brand);
  }

  @Test
  void shouldFindBrandByName() {
    Brand brand = getTestBrand();

    Mockito.when(brandDAOImpl.create(brand)).thenReturn(brand);
    assertNotNull(brandServiceImpl.create(brand));

    Mockito.when(brandDAOImpl.findByName(brand.getName())).thenReturn(brand);
    Brand foundBrand = brandServiceImpl.findBrandByName(brand.getName());
    assertNotNull(foundBrand);
    assertEquals(foundBrand, brand);
  }

  @Test
  void shouldGetAllBrands() {
    Brand brand = getTestBrand();
    Brand brand1 = new Brand(2, "name", "email", 1);

    Mockito.when(brandDAOImpl.create(brand)).thenReturn(brand);
    assertNotNull(brandServiceImpl.create(brand));

    Mockito.when(brandDAOImpl.create(brand1)).thenReturn(brand1);
    assertNotNull(brandServiceImpl.create(brand1));

    List<Brand> brands = new ArrayList<>();
    brands.add(brand);
    brands.add(brand1);

    Mockito.when(brandDAOImpl.getAll()).thenReturn(brands);
    List<Brand> testList = brandServiceImpl.getAllBrands();
    assertNotNull(testList);
    assertEquals(testList,brands);
  }
}
