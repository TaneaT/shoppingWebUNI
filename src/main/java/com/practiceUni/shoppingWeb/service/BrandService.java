package com.practiceUni.shoppingWeb.service;

import com.practiceUni.shoppingWeb.domain.Brand;

import java.util.List;

public interface BrandService {

    Brand create(Brand brand);

    Brand update(Brand brand);

    boolean deleteById(Integer id);

    Brand findBrandById(Integer id);

    Brand findBrandByName(String name);

    List<Brand> getAllBrands();

}
