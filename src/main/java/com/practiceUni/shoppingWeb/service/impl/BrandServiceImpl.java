package com.practiceUni.shoppingWeb.service.impl;

import com.practiceUni.shoppingWeb.dao.BrandDAO;
import com.practiceUni.shoppingWeb.domain.Brand;
import com.practiceUni.shoppingWeb.service.BrandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BrandServiceImpl.class);
    private final BrandDAO brandDAO;

    public BrandServiceImpl(BrandDAO brandDAO) {
        this.brandDAO = brandDAO;
    }

    @Override
    public Brand create(Brand brand) {
        return brandDAO.create(brand);
    }

    @Override
    public Brand update(Brand brand) {
        if(brand.getId() == null){
            return brandDAO.create(brand);
        } else {
      return brandDAO.update(brand);
        }
    }

    @Override
    public boolean deleteById(Integer id) {
    if (id == null) {
        LOGGER.error("Failed to delete the brand due to null Id");
      return false;
        } else {
        return brandDAO.deleteById(id);
    }
    }

    @Override
    public Brand findBrandById(Integer id) {
        Brand brand = brandDAO.findById(id);

        if(brand == null){
            LOGGER.error("Failed to find a brand due to wrong Id: " + id);
            return null;
        } else {
      return brand;
    }
  }

    @Override
    public Brand findBrandByName(String name) {
        Brand brand = brandDAO.findByName(name);

        if(brand == null){
            LOGGER.error("Failed to find a brand due to wrong name: " + name);
            return null;
        }

        return brand;
    }

    @Override
    public List<Brand> getAllBrands() {
        return brandDAO.getAll();
    }
}
