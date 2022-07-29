package ro.msg.learning.shop.dto.savedtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ro.msg.learning.shop.model.ProductCategory;

import java.util.ArrayList;

@Data
@Builder
@AllArgsConstructor
public class SaveProductCategoryDto {
    private final String name;
    private final String description;

    public ProductCategory toProductCategory() {
        return new ProductCategory(null, name, description, new ArrayList<>());
    }
}
