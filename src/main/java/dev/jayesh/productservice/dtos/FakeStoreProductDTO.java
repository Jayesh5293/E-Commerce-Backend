package dev.jayesh.productservice.dtos;

import dev.jayesh.productservice.models.Category;
import dev.jayesh.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDTO {

    private long id;
    private String title, image, description, category;
    private double price;

    public Product toProduct() {
        Product product = new Product();
        product.setId(getId());
        product.setTitle(getTitle());
        product.setDescription(getDescription());
        product.setImageURL(getImage());
        product.setPrice(getPrice());

        Category category = new Category();
        category.setName(getCategory());
        product.setCategory(category);

        return product;
    }
}
