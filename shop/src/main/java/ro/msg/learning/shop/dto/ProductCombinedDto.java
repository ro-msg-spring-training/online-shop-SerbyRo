package ro.msg.learning.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ro.msg.learning.shop.model.Product;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
public class ProductCombinedDto {
    private Long productId;
    private String name;
    private String description;
    private BigDecimal price;
    private Double weight;
    private ProductCategoryDto category;
    private SupplierDto supplier;
    private String imageUrl;


}
