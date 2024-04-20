package dev.jayesh.productservice.services;

import dev.jayesh.productservice.dtos.FakeStoreProductDTO;
import dev.jayesh.productservice.models.Category;
import dev.jayesh.productservice.models.Product;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService {

    private final RestTemplate mRestTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.mRestTemplate = restTemplate;
    }

    @Override
    public void createProduct(String title, String image, String description, String category, double price) {
        FakeStoreProductDTO fakeStoreProductDTO = new FakeStoreProductDTO();

        fakeStoreProductDTO.setTitle(title);
        fakeStoreProductDTO.setImage(image);
        fakeStoreProductDTO.setDescription(description);
        fakeStoreProductDTO.setCategory(category);
        fakeStoreProductDTO.setPrice(price);

        FakeStoreProductDTO response = mRestTemplate.postForObject("", fakeStoreProductDTO, FakeStoreProductDTO.class);
        return;
    }

    @Override
    public Product getProduct(long id) {
        FakeStoreProductDTO fakeStoreProductDto = mRestTemplate
                .getForObject(
                        "https://fakestoreapi.com/products/" + id,
                        FakeStoreProductDTO.class);

        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setImageURL(fakeStoreProductDto.getImage());

        Category category = new Category();
        category.setName(fakeStoreProductDto.getCategory());
        product.setCategory(category);

        return product;
    }

    @Override
    public Product getProductByCategory(Category category) {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> allProducts = new ArrayList<>();
        FakeStoreProductDTO fakeStoreProductDTO = mRestTemplate.
                getForObject("https://fakestoreapi.com/products", FakeStoreProductDTO.class);
        return allProducts;
    }

    @Override
    public List<Category> getAllCategories() {

        return null;
    }

    @Override
    public void updateProduct(long id, Product updatedProduct) {
        // Retrieving the existing product by its ID.
        Product curentProduct = getProduct(id);

        if (curentProduct == null) return;
        // Now update the existing product with updated details.
        curentProduct.setTitle(updatedProduct.getTitle());
        curentProduct.setDescription(updatedProduct.getDescription());
        curentProduct.setImageURL(updatedProduct.getImageURL());
        curentProduct.setCategory(updatedProduct.getCategory());

        /* Now, sending the updated details to API for update. */
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Product> requestEntity = new HttpEntity<>(curentProduct, headers);
        ResponseEntity<Void> responseEntity = mRestTemplate
                        .exchange("https://fakestoreapi.com/products" + id
                        , HttpMethod.PUT, requestEntity, Void.class);

        System.out.println("Product is updated");
    }

    @Override
    public void deleteProduct(long id) {

    }
}
