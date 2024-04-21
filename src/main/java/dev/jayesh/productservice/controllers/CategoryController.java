package dev.jayesh.productservice.controllers;

import dev.jayesh.productservice.models.Category;
import dev.jayesh.productservice.services.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController {
    private final ProductService mProductService;

    public CategoryController(ProductService productService) {
        mProductService = productService;
    }

    @GetMapping("/products/category")
    public List<Category> getAllCategories() {
        return mProductService.getAllCategories();
    }
}
