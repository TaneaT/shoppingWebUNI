//package com.practiceUni.shoppingWeb.controller;
//
//import com.practiceUni.shoppingWeb.domain.Brand;
//import com.practiceUni.shoppingWeb.domain.Product;
//import com.practiceUni.shoppingWeb.service.impl.ProductServiceImpl;
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
//class ProductControllerTest {
//
//    @Mock
//    private ProductServiceImpl productServiceImpl;
//
//    private ProductController productController;
//
//    @BeforeEach
//    void setUp() {
//        productController = new ProductController(productServiceImpl);
//      }
//
//      private Product getTestProduct(){
//        return new Product(1,"Name","size","color", 1);
//      }
//
//    @Test
//    void shouldCreateProduct() {
//        Product product = getTestProduct();
//
//        Mockito.when(productServiceImpl.createProduct(product)).thenReturn(product);
//        assertNotNull(productController.createProduct(product));
//
//        Mockito.when(productServiceImpl.findProductById(product.getId())).thenReturn(product);
//        Product testProduct = productController.findProductById(product.getId());
//        assertNotNull(testProduct);
//        assertEquals(testProduct,product);
//
//      }
//
//    @Test
//    void shouldUpdateProduct() {
//        Product product = getTestProduct();
//
//        Mockito.when(productServiceImpl.createProduct(product)).thenReturn(product);
//        assertNotNull(productController.createProduct(product));
//
//        product.setQuantity(2);
//
//        Mockito.when(productServiceImpl.updateProduct(product)).thenReturn(product);
//        Product updatedProduct = productController.updateProduct(product);
//        assertNotNull(updatedProduct);
//
//        Mockito.when(productServiceImpl.findProductById(updatedProduct.getId())).thenReturn(product);
//        Product foundProduct = productController.findProductById(updatedProduct.getId());
//        assertNotNull(foundProduct);
//        assertEquals(foundProduct,updatedProduct);
//
//      }
//
//    @Test
//    void deleteProductById() {
//        Product product = getTestProduct();
//
//        Mockito.when(productServiceImpl.createProduct(product)).thenReturn(product);
//        assertNotNull(productController.createProduct(product));
//
//        Mockito.when(productServiceImpl.deleteProductById(product.getId())).thenReturn(true);
//        assertTrue(productController.deleteProductById(product.getId()));
//
//      }
//
//    @Test
//    void findProductById() {
//        Product product =getTestProduct();
//
//        Mockito.when(productServiceImpl.createProduct(product)).thenReturn(product);
//        assertNotNull(productController.createProduct(product));
//
//        Mockito.when(productServiceImpl.findProductById(product.getId())).thenReturn(product);
//        Product foundProduct = productController.findProductById(product.getId());
//        assertNotNull(foundProduct);
//        assertEquals(foundProduct,product);
//
//      }
//
//    @Test
//    void findProductByName() {
//        Product product = getTestProduct();
//
//        Mockito.when(productServiceImpl.createProduct(product)).thenReturn(product);
//        assertNotNull(productController.createProduct(product));
//
//        Mockito.when(productServiceImpl.findProductByName(product.getName())).thenReturn(product);
//        Product foundProduct = productController.findProductByName(product.getName());
//        assertNotNull(foundProduct);
//        assertEquals(foundProduct,product);
//
//      }
//
//      @Test
//    void shouldGetAllProducts(){
//          Product product1 = new Product(1,"Name", "size", "color", 1);
//          Product product2 = new Product(2,"Name1", "size1", "color1", 2);
//
//          Mockito.when(productServiceImpl.createProduct(product1)).thenReturn(product1);
//          Mockito.when(productServiceImpl.createProduct(product2)).thenReturn(product2);
//          assertNotNull(productController.createProduct(product1));
//          assertNotNull(productController.createProduct(product2));
//
//          List<Product> productList = new ArrayList<>();
//
//          productList.add(product1);
//          productList.add(product2);
//          assertEquals(productList.size(),2);
//
//
//          Mockito.when(productServiceImpl.getAllProducts()).thenReturn(productList);
//          List<Product> testList = productController.getAllProducts();
//          assertNotNull(testList);
//          assertEquals(testList,productList);
//
//
//      }
//}