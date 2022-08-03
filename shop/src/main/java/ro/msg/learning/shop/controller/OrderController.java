package ro.msg.learning.shop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.controller.mappers.OrderDetailMapper;
import ro.msg.learning.shop.controller.mappers.OrderMapper;
import ro.msg.learning.shop.dto.CreateOrderDto;
import ro.msg.learning.shop.dto.OrderDto;
import ro.msg.learning.shop.model.ProductOrder;
import ro.msg.learning.shop.service.OrderService;

import java.util.stream.Collectors;

@RestController
public class OrderController {

    private final OrderService orderService;
    private final OrderDetailMapper orderDetailMapper;
    private final OrderMapper orderMapper;

    //private Mapper mapper=new Mapper();

    public OrderController(OrderService orderService, OrderDetailMapper orderDetailMapper, OrderMapper orderMapper)
    {
        this.orderService = orderService;
        this.orderDetailMapper = orderDetailMapper;
        this.orderMapper = orderMapper;
    }

    @PostMapping("/orders")
    public ResponseEntity<CreateOrderDto> createOrder(@RequestBody CreateOrderDto createOrderDto)
    {
        ProductOrder productOrder = orderService.placeOrder(orderMapper.toOrderEntity(createOrderDto), createOrderDto.getCustomerID(),
                createOrderDto.getProductOrders().stream().map(orderDetailMapper::toProductOrderDetailEntity).collect(Collectors.toList()));
        return new ResponseEntity<>(orderMapper.toOrderDto(productOrder), HttpStatus.CREATED);
    }
}
