package dev.jayesh.productservice.services;

import dev.jayesh.productservice.dtos.FakeStoreCategoryDTO;
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

    // here we are creating a new product by passing all product properties.
    @Override
    public Product createProduct(String title, String image, String description, String category, double price) {
        FakeStoreProductDTO fakeStoreProductDTO = new FakeStoreProductDTO();

        fakeStoreProductDTO.setTitle(title);
        fakeStoreProductDTO.setImage(image);
        fakeStoreProductDTO.setDescription(description);
        fakeStoreProductDTO.setCategory(category);
        fakeStoreProductDTO.setPrice(price);

        FakeStoreProductDTO response = mRestTemplate.postForObject("https://fakestoreapi.com/products", fakeStoreProductDTO, FakeStoreProductDTO.class);
        return response.toProduct();
    }

    // It is returning product by its id.
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
    public List<Product> getProductByCategory(Category category) {

        List<Product> products = new ArrayList<>();
        FakeStoreProductDTO[] productDTOS = mRestTemplate.getForObject("https://fakestoreapi.com/products/category/" + category, FakeStoreProductDTO[].class);

        if (productDTOS != null) {
            for (FakeStoreProductDTO fakeStoreProductDTO : productDTOS) {
                products.add(fakeStoreProductDTO.toProduct());
            }
        }
        return products;
    }

    /**
     * It will return all the products available on Fake Store.
     * @return List of Products.
     */
    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        FakeStoreProductDTO[] fakeStoreProductDTO = mRestTemplate.
                getForObject("https://fakestoreapi.com/products", FakeStoreProductDTO[].class);

        if (fakeStoreProductDTO != null) {
            for (FakeStoreProductDTO fakeStoreProductdto : fakeStoreProductDTO) {
                products.add(fakeStoreProductdto.toProduct());
            }
        }
        return products;
    }

    /**
     * it will return all the categories on Fake Store.
     * @return List of Categories.
     */
    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();

        FakeStoreCategoryDTO[] fakeStoreCategories = mRestTemplate.
                getForObject("https://fakestoreapi.com/products/categories", FakeStoreCategoryDTO[].class);
        if (fakeStoreCategories != null) {
            for (FakeStoreCategoryDTO fakeStoreCategoryDTO : fakeStoreCategories) {
                categories.add(fakeStoreCategoryDTO.toCategory());
            }
        }
        return categories;
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

    // Deleting a product through its ID.
    @Override
    public void deleteProduct(long id) {
        mRestTemplate.delete("https://fakestoreapi.com/products/" + id);
    }
}
