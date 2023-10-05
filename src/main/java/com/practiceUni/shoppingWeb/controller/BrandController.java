package com.practiceUni.shoppingWeb.controller;

import com.practiceUni.shoppingWeb.domain.Brand;
import com.practiceUni.shoppingWeb.domain.Product;
import com.practiceUni.shoppingWeb.service.BrandService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("api/brand")
public class BrandController {
  private final BrandService brandService;

  public BrandController(BrandService brandService) {
    this.brandService = brandService;
  }



  @PostMapping("/create")
  public String addBrand(@ModelAttribute Brand brand, RedirectAttributes redirectAttributes) {
    Brand isBrandAdded = brandService.create(brand);

    if(isBrandAdded != null) {
      redirectAttributes.addFlashAttribute("successMessage", "Brand added successfully");
    } else {
      redirectAttributes.addFlashAttribute("errorMessage", "Failed to add brand");
    }

    return "redirect:/api/user/profile";
  }

  @PostMapping("/update")
  public String updateBrand(@RequestParam Integer brandId, @RequestParam String newEmail, RedirectAttributes redirectAttributes) {
    Brand brand = brandService.findBrandById(brandId);
    if (brand != null) {
      brand.setEmail(newEmail);
      brandService.update(brand);
      redirectAttributes.addFlashAttribute("successMessage", "Brand updated successfully");
    } else {
      redirectAttributes.addFlashAttribute("errorMessage", "Failed to update brand");
    }

    return "redirect:/api/user/profile";
  }


  @PostMapping("/delete")
  public String deleteBrand(@RequestParam("brandId") Integer brandId, RedirectAttributes redirectAttributes) {
    boolean isBrandDeleted = brandService.deleteById(brandId);
    if (isBrandDeleted) {
      redirectAttributes.addFlashAttribute("successMessage", "Brand deleted successfully");
    } else {
      redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete brand");
    }
    return "redirect:/api/user/profile";
  }

  @GetMapping("/find/id/{id}")
  public Brand findBrandById(@PathVariable Integer id) {
    return brandService.findBrandById(id);
  }

  @GetMapping("/find/name/{name}")
  public Brand findBrandByName(@PathVariable String name) {
    return brandService.findBrandByName(name);
  }


  @GetMapping("/all")
  public String getAllBrands(Model model) {

    List<Brand> brands = brandService.getAllBrands();
            model.addAttribute("brands", brands);

    return "redirect:/";
  }

}
