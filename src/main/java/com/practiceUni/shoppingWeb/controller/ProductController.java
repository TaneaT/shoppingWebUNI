package com.practiceUni.shoppingWeb.controller;

import com.practiceUni.shoppingWeb.domain.Product;
import com.practiceUni.shoppingWeb.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @PostMapping("/create")
    public Product createProduct(@RequestBody Product product){
        return productService.createProduct(product);
    }

    @PutMapping("/update")
    public Product updateProduct(@RequestBody Product product){
        return productService.updateProduct(product);
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
