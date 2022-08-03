package ro.msg.learning.shop.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
//TODO refactor the name ProductDto
public class CreateProductDto {
    private long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Double weight;
    private String imageUrl;
    private Long productCategoryId;
    private Long supplierId;
}
