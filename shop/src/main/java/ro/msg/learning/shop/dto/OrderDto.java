package ro.msg.learning.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {
    private Long orderId;
    private Long customerID;
    private Long shipped_from_id;
    private List<OrderDetailDto> productOrders;
    private LocalDate createdTime;
    private AddressDto deliveryAddress;
}
