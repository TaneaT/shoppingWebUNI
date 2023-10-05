package com.practiceUni.shoppingWeb.service.impl;

import com.practiceUni.shoppingWeb.dao.impl.ProductDAOImpl;
import com.practiceUni.shoppingWeb.domain.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductDAOImpl productDAOImpl;

    private ProductServiceImpl productServiceImpl;

    @BeforeEach
    void setUp() {
        productServiceImpl = new ProductServiceImpl(productDAOImpl);
      }

      private Product getTestProduct(){
          return new Product(1,"Name","category","size", "color",1);
      }

    @Test
    void shouldCreateProduct() {
        Product product = getTestProduct();

        Mockito.when(productDAOImpl.create(product)).thenReturn(product);
        assertNotNull(productServiceImpl.createProduct(product));

        Mockito.when(productDAOImpl.findById(product.getId())).thenReturn(product);
        Product testProduct = productServiceImpl.findProductById(product.getId());
        assertNotNull(testProduct);
        assertEquals(testProduct,product);

      }

    @Test
    void shouldUpdateProduct() {
        Product product = getTestProduct();

        Mockito.when(productDAOImpl.create(product)).thenReturn(product);
        assertNotNull(productServiceImpl.createProduct(product));

        Mockito.when(productDAOImpl.update(product)).thenReturn(product);
        Product updatedProduct = productServiceImpl.updateProduct(product);
        assertNotNull(updatedProduct);

        Mockito.when(productDAOImpl.findById(updatedProduct.getId())).thenReturn(product);
        Product foundProduct = productServiceImpl.findProductById(product.getId());
        assertNotNull(foundProduct);
        assertEquals(foundProduct,updatedProduct);

      }

      @Test
      void shouldCreateProductIfNotFound(){
        Product product = new Product("Name","category","size", "color",1);

        Mockito.lenient().when(productDAOImpl.findById(product.getId())).thenReturn(null);
        assertNull(productServiceImpl.findProductById(product.getId()));

        Mockito.lenient().when(productDAOImpl.create(product)).thenReturn(product);
        product.setId(1);
        assertNotNull(productServiceImpl.createProduct(product));

        Mockito.when(productDAOImpl.findById(product.getId())).thenReturn(product);
        Product testProduct = productServiceImpl.findProductById(product.getId());
        assertNotNull(testProduct);
        assertEquals(testProduct,product);


      }

    @Test
    void shouldDeleteProductById() {
        Product product = getTestProduct();
        Mockito.when(productDAOImpl.create(product)).thenReturn(product);
        assertNotNull(productServiceImpl.createProduct(product));

        Mockito.when(productDAOImpl.deleteById(product.getId())).thenReturn(true);
        assertTrue(productServiceImpl.deleteProductById(product.getId()));

      }

    @Test
    void shouldFindProductById() {
        Product product = getTestProduct();

        Mockito.when(productDAOImpl.create(product)).thenReturn(product);
        assertNotNull(productServiceImpl.createProduct(product));

        Mockito.when(productDAOImpl.findById(product.getId())).thenReturn(product);
        Product foundProduct = productServiceImpl.findProductById(product.getId());
        assertNotNull(foundProduct);
        assertEquals(foundProduct,product);

      }

    @Test
    void shouldFindProductByName() {
        Product product = getTestProduct();

        Mockito.when(productDAOImpl.create(product)).thenReturn(product);
        assertNotNull(productServiceImpl.createProduct(product));

        Mockito.when(productDAOImpl.findByName(product.getName())).thenReturn(product);
        Product foundProduct = productServiceImpl.findProductByName(product.getName());
        assertNotNull(foundProduct);
        assertEquals(foundProduct,product);

      }

      @Test
    void shouldGetAllProducts(){
          Product product1 = new Product(1,"Name","category","size", "color",1);
          Product product2 = new Product(2,"Name","category","size", "color",1);

          Mockito.when(productDAOImpl.create(product1)).thenReturn(product1);
          Mockito.when(productDAOImpl.create(product2)).thenReturn(product2);
          assertNotNull(productServiceImpl.createProduct(product1));
          assertNotNull(productServiceImpl.createProduct(product2));

          List<Product> productList = new ArrayList<>();

        productList.add(product1);
        productList.add(product2);

        assertEquals(productList.size(),2);

        Mockito.when(productDAOImpl.getAllProducts()).thenReturn(productList);
        List<Product> testList = productServiceImpl.getAllProducts();

        assertNotNull(testList);
        assertEquals(testList,productList);

      }

}