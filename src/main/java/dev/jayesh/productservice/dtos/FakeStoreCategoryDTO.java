package dev.jayesh.productservice.dtos;

import dev.jayesh.productservice.models.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreCategoryDTO {

    private long id;
    private String name;

    public Category toCategory() {
        Category category = new Category();
        category.setId(getId());
        category.setName(getName());
        return category;
    }
}
