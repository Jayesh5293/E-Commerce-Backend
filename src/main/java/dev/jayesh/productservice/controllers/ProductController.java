package dev.jayesh.productservice.controllers;

import dev.jayesh.productservice.dtos.CreateProductRequestDTO;
import dev.jayesh.productservice.models.Category;
import dev.jayesh.productservice.models.Product;
import dev.jayesh.productservice.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    ProductService mProductService;

    ProductController(ProductService productService) {
        this.mProductService = productService;
    }

    @PostMapping("/products")
    public Product createProduct(@RequestBody CreateProductRequestDTO createProductRequestDTO) {
        return mProductService.createProduct(createProductRequestDTO.getTitle(),
                createProductRequestDTO.getImage(),
                createProductRequestDTO.getDescription(),
                createProductRequestDTO.getCategory(),
                createProductRequestDTO.getPrice());
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
    public List<Product> getProductByCategory(@PathVariable Category productCategory) {
        return mProductService.getProductByCategory(productCategory);
    }

    @DeleteMapping("/product/{id}")
    public void deleteProduct(@PathVariable long id) {
        mProductService.deleteProduct(id);
    }
}
