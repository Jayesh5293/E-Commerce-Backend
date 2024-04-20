package dev.jayesh.productservice.controllers;

import dev.jayesh.productservice.models.Category;
import dev.jayesh.productservice.models.Product;
import dev.jayesh.productservice.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductManager {
    ProductService mProductService;

    ProductManager(ProductService productService) {
        this.mProductService = productService;
    }

    @PostMapping
    public void createProduct() {

    }

    @PutMapping("/product/{id}")
    public void updateProduct(@PathVariable long id, @RequestBody Product newProduct) {
        mProductService.updateProduct(id, newProduct);
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return mProductService.getAllProducts();
    }

    @GetMapping("/product/{id}")
    public Product getProductByID(@PathVariable long id) {
        return mProductService.getProduct(id);
    }

    @GetMapping("/products/category/{productCategory}")
    public Product getProductByCategory(@PathVariable Category productCategory) {
        return mProductService.getProductByCategory(productCategory);
    }

    @DeleteMapping("/product/{id}")
    public void deleteProduct(@PathVariable long id) {
        mProductService.deleteProduct(id);
    }
}
