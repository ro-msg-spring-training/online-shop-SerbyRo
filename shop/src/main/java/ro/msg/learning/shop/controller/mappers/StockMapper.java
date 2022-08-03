package ro.msg.learning.shop.controller.mappers;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dto.StockDto;
import ro.msg.learning.shop.model.Location;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.model.Stock;

@Component

public class StockMapper {
    public Stock toStockEntity(StockDto stockDto)
    {
        Product product = Product.builder().name(stockDto.getProductName()).build();
        Location location = Location.builder().name(stockDto.getLocationName()).build();
        return Stock.builder()
                .productId(product.getId())
                .locationId(location.getId())
                .product(product)
                .location(location)
                .quantity(stockDto.getQuantity())
                .build();
    }

}
