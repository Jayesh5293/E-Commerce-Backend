package dev.jayesh.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDTO {

    private long id;
    private String title, image, description, category;
    private double price;
}
