package ro.msg.learning.shop.dto.savedtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
public class SaveProductDto {
    private String name;
    private String description;
    private BigDecimal price;
    private double weight;
    private long productCategory;
    private long supplier;
    private String imageUrl;
}
