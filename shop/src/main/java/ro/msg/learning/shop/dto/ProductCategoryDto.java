package ro.msg.learning.shop.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ro.msg.learning.shop.model.ProductCategory;

import java.util.ArrayList;
import java.util.HashSet;

@Data
@Builder
@AllArgsConstructor
public class ProductCategoryDto {
    private Long categoryId;
    private String name;
    private String description;


}
