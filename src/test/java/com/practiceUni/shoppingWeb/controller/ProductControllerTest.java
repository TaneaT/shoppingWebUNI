package com.practiceUni.shoppingWeb.controller;

import com.practiceUni.shoppingWeb.controller.ProductController;
import com.practiceUni.shoppingWeb.domain.Product;
import com.practiceUni.shoppingWeb.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @Mock
    private ProductService productService;

    private ProductController productController;



    @BeforeEach
    void setUp() {
        productController = new ProductController(productService);
    }

    private Product getTestProduct(){
        return new Product(1,"Name","category","size", "color",1);
    }

    @Test
    void shouldAddProduct() {
        Product product = getTestProduct();

        when(productService.createProduct(any(Product.class))).thenReturn(product);

        MultipartFile productImage = Mockito.mock(MultipartFile.class);
        RedirectAttributes redirectAttributes = Mockito.mock(RedirectAttributes.class);

        String result = productController.addProduct(product, productImage, redirectAttributes);

        assertEquals("redirect:/api/user/profile", result);

        verify(productService, times(1)).createProduct(any(Product.class));
        verify(redirectAttributes, times(1)).addFlashAttribute(eq("successMessage"), anyString());
    }

    @Test
    void shouldUpdateProduct() {
        Product product = getTestProduct();
        product.setQuantity(2);

        when(productService.findProductById(product.getId())).thenReturn(product);
        when(productService.updateProduct(any(Product.class))).thenReturn(product);

        RedirectAttributes redirectAttributes = Mockito.mock(RedirectAttributes.class);

        String result = productController.updateProduct(product.getId(), 2, redirectAttributes);

        assertEquals("redirect:/api/user/profile", result);

        verify(productService, times(1)).findProductById(product.getId());
        verify(productService, times(1)).updateProduct(any(Product.class));
        verify(redirectAttributes, times(1)).addFlashAttribute(eq("successMessage"), anyString());
    }

    @Test
    void shouldDeleteProduct() {
        int productId = 1;

        when(productService.deleteProductById(productId)).thenReturn(true);

        RedirectAttributes redirectAttributes = Mockito.mock(RedirectAttributes.class);

        String result = productController.deleteProduct(productId, redirectAttributes);

        assertEquals("redirect:/api/user/profile", result);

        verify(productService, times(1)).deleteProductById(productId);
        verify(redirectAttributes, times(1)).addFlashAttribute(eq("successMessage"), anyString());
    }

    @Test
    void shouldFindProductById() {
        Product product = getTestProduct();

        when(productService.findProductById(product.getId())).thenReturn(product);

        Product result = productController.findProductById(product.getId());

        assertNotNull(result);
        assertEquals(result, product);
    }

    @Test
    void shouldFindProductByName() {
        Product product = getTestProduct();

        when(productService.findProductByName(product.getName())).thenReturn(product);

        Product result = productController.findProductByName(product.getName());

        assertNotNull(result);
        assertEquals(result, product);
    }

    @Test
    void shouldGetAllProducts() {
        List<Product> productList = new ArrayList<>();
        Product product1 = new Product(1,"Name","category","size", "color",1);
        Product product2 = new Product(2,"Name","category","size", "color",2);
        productList.add(product1);
        productList.add(product2);

        when(productService.getAllProducts()).thenReturn(productList);

        List<Product> result = productController.getAllProducts();

        assertNotNull(result);
        assertEquals(result, productList);
    }
}
