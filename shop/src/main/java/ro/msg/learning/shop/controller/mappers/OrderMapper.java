package ro.msg.learning.shop.controller.mappers;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dto.AddressDto;
import ro.msg.learning.shop.dto.CreateOrderDto;
import ro.msg.learning.shop.dto.CreateProductDto;
import ro.msg.learning.shop.model.ProductOrder;

import java.util.stream.Collectors;

@Component
public class OrderMapper {

    private OrderDetailMapper orderDetailMapper;

    public OrderMapper(OrderDetailMapper orderDetailMapper)
    {
        this.orderDetailMapper=orderDetailMapper;
    }

    public ProductOrder toOrderEntity(CreateOrderDto createOrderDto)
    {
        return ProductOrder.builder()
                .id(createOrderDto.getOrderId())
                .createdAt(createOrderDto.getCreatedTime())
                .address_country(createOrderDto.getDeliveryAddress().getAddressCountry())
                .address_county(createOrderDto.getDeliveryAddress().getAddressCounty())
                .address_city(createOrderDto.getDeliveryAddress().getAddressCity())
                .address_street_address(createOrderDto.getDeliveryAddress().getAddressStreet())
                .build();
    }

    public CreateOrderDto toOrderDto(ProductOrder productOrder)
    {
        return CreateOrderDto.builder()
                .orderId(productOrder.getId())
                .customerID(productOrder.getCustomer().getId())
                .createdTime(productOrder.getCreatedAt())
                .deliveryAddress(new AddressDto(productOrder.getAddress_country(), productOrder.getAddress_county(), productOrder.getAddress_city(), productOrder.getAddress_street_address()))
                .productOrders(productOrder.getOrderDetails().stream().map(orderDetailMapper::toProductOrderDetailDto).collect(Collectors.toList()))
                .build();
    }
}
