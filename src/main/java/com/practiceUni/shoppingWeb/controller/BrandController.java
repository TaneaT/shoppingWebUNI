package com.practiceUni.shoppingWeb.controller;

import com.practiceUni.shoppingWeb.domain.Brand;
import com.practiceUni.shoppingWeb.service.BrandService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/brand")
public class BrandController {

    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @PostMapping("/create")
    public Brand createBrand(@RequestBody Brand brand){
        return brandService.create(brand);
    }

    @PutMapping("/update")
    public Brand updateBrand(@RequestBody Brand brand){
        return brandService.update(brand);
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteBrandById(@PathVariable Integer id){
        return brandService.deleteById(id);
    }

    @GetMapping("/find/id/{id}")
    public Brand findBrandById(@PathVariable Integer id){
        return brandService.findBrandById(id);
    }

    @GetMapping("/find/name/{name}")
    public Brand findBrandByName(@PathVariable String name){
        return brandService.findBrandByName(name);
    }

    @GetMapping("/find/all")
    public List<Brand> getAllBrands(){
        return brandService.getAllBrands();
    }

}
