package ro.msg.learning.shop.controller.mappers;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dto.OrderDetailDto;
import ro.msg.learning.shop.model.OrderDetail;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.model.ProductOrderDetail;

@Component
public class OrderDetailMapper {
    public ProductOrderDetail toProductOrderDetailEntity(OrderDetailDto orderDetailDto)
    {
        Product product = Product.builder()
                .id(orderDetailDto.getProductId())
                .build();
        return ProductOrderDetail.builder()
                .product(product)
                .quantity(orderDetailDto.getQuantity())
                .build();
    }

    public OrderDetailDto toProductOrderDetailDto(ProductOrderDetail productOrderDetail)
    {
        return OrderDetailDto.builder()
                .productId(productOrderDetail.getProduct().getId())
                .quantity(productOrderDetail.getQuantity())
                .build();
    }
}
