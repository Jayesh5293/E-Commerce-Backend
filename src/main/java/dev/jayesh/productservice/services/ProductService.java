package dev.jayesh.productservice.services;

import dev.jayesh.productservice.models.Category;
import dev.jayesh.productservice.models.Product;

import java.util.List;

public interface ProductService {

    Product createProduct(String title, String image, String description, String category, double price);

    Product getProduct(long id);

    List<Product> getProductByCategory(Category category);

    List<Product> getAllProducts();

    List<Category> getAllCategories();

    void updateProduct(long id, Product updatedProduct);

    void deleteProduct(long id);
}
