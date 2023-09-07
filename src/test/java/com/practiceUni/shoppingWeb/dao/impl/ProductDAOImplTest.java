package com.practiceUni.shoppingWeb.dao.impl;

import com.practiceUni.shoppingWeb.dao.ProductDAO;
import com.practiceUni.shoppingWeb.domain.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ProductDAOImplTest {

    @Autowired
    private ProductDAO productDAO;

    private Product getTestProduct(){
        return new Product("Name","size","color", 1);
    }

    @Test
    void shouldCreateProduct() {
        Product product = getTestProduct();
        assertNotNull(productDAO.create(product));

        Product testProduct = productDAO.findById(product.getId());
        assertNotNull(testProduct);
        assertEquals(testProduct,product);

        assertTrue(productDAO.deleteById(product.getId()));

      }

    @Test
    void shouldUpdateProduct() {
        Product product = getTestProduct();
        assertNotNull(productDAO.create(product));

        product.setQuantity(2);

        Product updatedProduct = productDAO.update(product);
        assertNotNull(updatedProduct);

        Product testProduct = productDAO.findById(updatedProduct.getId());
        assertNotNull(testProduct);
        assertEquals(testProduct,updatedProduct);

        assertTrue(productDAO.deleteById(product.getId()));

      }

      @Test
      void shouldCreateProductIfNotFound(){
        Product product = getTestProduct();

        assertNull(productDAO.findById(product.getId()));

        assertNotNull(productDAO.create(product));

        Product testProduct = productDAO.findById(product.getId());
        assertNotNull(testProduct);
        assertEquals(testProduct,product);

        assertTrue(productDAO.deleteById(product.getId()));

      }

    @Test
    void shouldDeleteProductById() {
        Product product = getTestProduct();
        assertNotNull(productDAO.create(product));

        assertTrue(productDAO.deleteById(product.getId()));

      }

    @Test
    void shouldFindProductById() {
        Product product = getTestProduct();

        assertNotNull(productDAO.create(product));

        Product foundProduct = productDAO.findById(product.getId());
        assertNotNull(foundProduct);
        assertEquals(foundProduct,product);

        assertTrue(productDAO.deleteById(product.getId()));

      }

    @Test
    void shouldFindProductByName() {
        Product product = getTestProduct();
        assertNotNull(productDAO.create(product));

        Product foundProduct = productDAO.findByName(product.getName());
        assertNotNull(foundProduct);
        assertEquals(foundProduct,product);

        assertTrue(productDAO.deleteById(product.getId()));

      }
}