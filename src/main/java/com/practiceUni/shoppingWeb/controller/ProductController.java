package com.practiceUni.shoppingWeb.controller;

import com.practiceUni.shoppingWeb.domain.Product;
import com.practiceUni.shoppingWeb.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("api/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/create")
    public String addProduct(@ModelAttribute Product product, RedirectAttributes redirectAttributes) {
        Product isProductAdded = productService.createProduct(product);

        if(isProductAdded != null) {
            redirectAttributes.addFlashAttribute("successMessage", "Product added successfully");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to add product");
        }

        return "redirect:/api/user/profile";
    }

    @PostMapping("/update")
    public String updateProduct(@RequestParam Integer productId, @RequestParam Integer newQuantity, RedirectAttributes redirectAttributes) {
        Product product = productService.findProductById(productId);
        if (product != null) {
            product.setQuantity(newQuantity);
            productService.updateProduct(product);
            redirectAttributes.addFlashAttribute("successMessage", "Product updated successfully");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to update product");
        }

        return "redirect:/api/user/profile";
    }



    @DeleteMapping("/delete/{id}")
    public boolean deleteProductById(@PathVariable Integer id){
        return productService.deleteProductById(id);
    }

    @GetMapping("/find/id/{id}")
    public Product findProductById (@PathVariable Integer id){
        return  productService.findProductById(id);
    }

    @GetMapping("/find/name/{name}")
    public Product findProductByName(@PathVariable String name){
        return productService.findProductByName(name);
    }

    @GetMapping("/find/all")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

}

