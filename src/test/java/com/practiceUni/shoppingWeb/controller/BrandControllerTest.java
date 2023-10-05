package com.practiceUni.shoppingWeb.controller;

import com.practiceUni.shoppingWeb.controller.BrandController;
import com.practiceUni.shoppingWeb.domain.Brand;
import com.practiceUni.shoppingWeb.service.BrandService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class BrandControllerTest {

    @Mock
    private BrandService brandService;

    private BrandController brandController;

@Autowired
    private MockMvc mockMvc;

    private Brand getTestBrand() {
        return new Brand(1, "name", "email", 1);
    }

    @BeforeEach
    void setUp() {
        brandController = new BrandController(brandService);
        mockMvc = MockMvcBuilders.standaloneSetup(brandController).build();
    }

    @Test
    void shouldCreateBrand() {
        Brand brand = getTestBrand();

        Mockito.when(brandService.create(brand)).thenReturn(brand);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/api/brand/create")
                .flashAttr("brand", brand);
        MvcResult result;
        try {
            result = mockMvc.perform(request)
                    .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Mockito.verify(brandService, Mockito.times(1)).create(brand);
    }


    @Test
    void shouldFindBrandById() {
        Integer brandId = 1;
        Brand brand = new Brand();
        Mockito.when(brandService.findBrandById(brandId)).thenReturn(brand);

        Brand result = brandController.findBrandById(brandId);

        assertNotNull(result);
        assertEquals(brand, result);
    }

    @Test
    void shouldFindBrandByName() {
        String brandName = "Test Brand";
        Brand brand = new Brand();
        Mockito.when(brandService.findBrandByName(brandName)).thenReturn(brand);

        Brand result = brandController.findBrandByName(brandName);

        assertNotNull(result);
        assertEquals(brand, result);
    }

    @Test
    void shouldGetAllBrands() {
        List<Brand> brands = new ArrayList<>();
        Mockito.when(brandService.getAllBrands()).thenReturn(brands);
        
        Model model = Mockito.mock(Model.class);

        String result = brandController.getAllBrands(model);

        assertEquals("redirect:/", result);

        Mockito.verify(brandService, Mockito.times(1)).getAllBrands();

        Mockito.verify(model, Mockito.times(1)).addAttribute("brands", brands);
    }

}
