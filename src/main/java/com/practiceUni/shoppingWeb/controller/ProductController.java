package com.practiceUni.shoppingWeb.controller;

import com.practiceUni.shoppingWeb.domain.Product;
import com.practiceUni.shoppingWeb.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;

@Controller
@RequestMapping("api/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/create")
    public String addProduct(@ModelAttribute Product product,
                             @RequestParam("productImage") MultipartFile productImage,
                             RedirectAttributes redirectAttributes) {
        try {
            if (productImage != null && !productImage.isEmpty()) {
                byte[] bytes = productImage.getBytes();
                String imageBase64 = Base64.getEncoder().encodeToString(bytes);
                product.setImageBase64(imageBase64);
            }
        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to process product image");
            return "redirect:/api/user/profile";
        }

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



    @PostMapping("/delete")
    public String deleteProduct(@RequestParam("productId") Integer productId, RedirectAttributes redirectAttributes) {
        boolean isProductDeleted = productService.deleteProductById(productId);
        if (isProductDeleted) {
            redirectAttributes.addFlashAttribute("successMessage", "Product deleted successfully");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete product");
        }
        return "redirect:/api/user/profile";
    }



    @GetMapping("/find/id/{id}")
    public Product findProductById (@PathVariable Integer id){
        return  productService.findProductById(id);
    }

    @GetMapping("/find/name/{name}")
    public Product findProductByName(@PathVariable String name){
        return productService.findProductByName(name);
    }

    @GetMapping("/men")
    public String getMenProducts(Model model) {
        List<Product> menProducts = productService.findProductByCategory("men");
        model.addAttribute("menProducts", menProducts);
        return "main";
    }

    @GetMapping("/women")
    public String getWomenProducts(Model model) {
        List<Product> womenProducts = productService.findProductByCategory("women");
        model.addAttribute("womenProducts", womenProducts);
        return "main";
    }

    @GetMapping("/kids")
    public String getKidsProducts(Model model) {
        List<Product> kidsProducts = productService.findProductByCategory("kids");
        model.addAttribute("kidsProducts", kidsProducts);
        return "main";
    }



    @GetMapping("/find/all")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

}

