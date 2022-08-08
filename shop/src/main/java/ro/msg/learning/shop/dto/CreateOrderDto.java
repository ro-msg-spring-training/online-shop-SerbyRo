package ro.msg.learning.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateOrderDto {
    private Long orderId;
    private Long customerID;
    private List<OrderDetailDto> productOrders;
    private LocalDate createdTime;
    private AddressDto deliveryAddress;
}
