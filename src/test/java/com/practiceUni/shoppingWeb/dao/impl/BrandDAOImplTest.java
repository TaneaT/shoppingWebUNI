package com.practiceUni.shoppingWeb.dao.impl;

import com.practiceUni.shoppingWeb.dao.BrandDAO;
import com.practiceUni.shoppingWeb.dao.ProductDAO;
import com.practiceUni.shoppingWeb.domain.Brand;
import com.practiceUni.shoppingWeb.domain.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BrandDAOImplTest {

    @Autowired
    private BrandDAO brandDAO;
    @Autowired
    private ProductDAO productDAO;



    @Test
    void shouldCreateBrand() {

        Product product =new Product("name","size","color",1) ;
        assertNotNull(productDAO.create(product));
        Brand brand = new Brand("name", "email",product.getId());
        assertNotNull(brandDAO.create(brand));

        Brand testBrand = brandDAO.findById(brand.getId());
        assertNotNull(testBrand);

        assertTrue(brandDAO.deleteById(brand.getId()));
        assertTrue(productDAO.deleteById(product.getId()));
      }

    @Test
    void shouldUpdateBrand() {
        Product product = new Product("name","size","color",1);
        assertNotNull(productDAO.create(product));
        Brand brand =  new Brand("name", "email", product.getId());
        assertNotNull(brandDAO.create(brand));

        brand.setEmail("new email");

        Brand updatedBrand = brandDAO.update(brand);
        assertNotNull(updatedBrand);

        Brand testBrand = brandDAO.findById(updatedBrand.getId());
        assertNotNull(testBrand);


        assertTrue(brandDAO.deleteById(brand.getId()));
        assertTrue(productDAO.deleteById(product.getId()));

      }

      @Test
      void shouldCreateBrandIfNotFound(){
          Product product = new Product("name","size","color",1);
          assertNotNull(productDAO.create(product));
          Brand brand =  new Brand("name", "email", product.getId());
          assertNull(brandDAO.findById(brand.getId()));

          assertNotNull(brandDAO.create(brand));

          Brand testBrand = brandDAO.findById(brand.getId());
          assertNotNull(testBrand);

          assertTrue(brandDAO.deleteById(brand.getId()));
          assertTrue(productDAO.deleteById(product.getId()));

      }

    @Test
    void shouldDeleteBrandById() {
        Product product = new Product("name","size","color",1);
        assertNotNull(productDAO.create(product));
        Brand brand =  new Brand("name", "email", product.getId());
        assertNotNull(brandDAO.create(brand));

        assertTrue(productDAO.deleteById(product.getId()));
        assertTrue(brandDAO.deleteById(brand.getId()));

      }

    @Test
    void shouldFindBrandById() {
        Product product = new Product("name","size","color",1);
        assertNotNull(productDAO.create(product));
        Brand brand =  new Brand("name", "email", product.getId());
        assertNotNull(brandDAO.create(brand));

        Brand foundBrand = brandDAO.findById(brand.getId());
        assertNotNull(foundBrand);

        assertTrue(productDAO.deleteById(product.getId()));
        assertTrue(brandDAO.deleteById(brand.getId()));

      }

    @Test
    void shouldFindBrandByName() {
        Product product = new Product("name","size","color",1);
        assertNotNull(productDAO.create(product));
        Brand brand =  new Brand("name", "email", product.getId());
        assertNotNull(brandDAO.create(brand));

        Brand foundBrand = brandDAO.findByName(brand.getName());
        assertNotNull(foundBrand);

        assertTrue(productDAO.deleteById(product.getId()));
        assertTrue(brandDAO.deleteById(brand.getId()));
      }

    @Test
    void shouldGetAllBrands() {
        Product product = new Product("name","size","color",1);
        assertNotNull(productDAO.create(product));
        Brand brand =  new Brand("name", "email", product.getId());
        assertNotNull(brandDAO.create(brand));

        List<Brand> brands = brandDAO.getAll();
        assertNotNull(brands);
        assertEquals(brands.size(),1);

        assertTrue(productDAO.deleteById(product.getId()));
        assertTrue(brandDAO.deleteById(brand.getId()));
      }
}