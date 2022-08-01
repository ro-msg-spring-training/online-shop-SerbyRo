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

//    public ProductCategoryDto(ProductCategory productCategory)
//    {
//        this.categoryId = productCategory.getId();
//        this.name = productCategory.getName();
//        this.description= productCategory.getDescription();
//    }
//
//    public ProductCategory toProductCategory(){
//        return new ProductCategory(categoryId,name,description,new HashSet<>());
//    }
}
