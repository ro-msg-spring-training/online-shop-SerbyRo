package ro.msg.learning.shop.controller.mappers;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dto.ProductCategoryDto;
import ro.msg.learning.shop.model.ProductCategory;

@Component
public class ProductCategoryMapper {
    public ProductCategory toProductCategoryEntity(ProductCategoryDto productCategoryDto)
    {
        return ProductCategory.builder()
                .id(productCategoryDto.getCategoryId())
                .name(productCategoryDto.getName())
                .description(productCategoryDto.getDescription())
                .build();
    }
    public ProductCategoryDto toProductCategoryDto(ProductCategory productCategory)
    {
        return ProductCategoryDto.builder()
                .categoryId(productCategory.getId())
                .name(productCategory.getName())
                .description(productCategory.getDescription())
                .build();
    }
}
