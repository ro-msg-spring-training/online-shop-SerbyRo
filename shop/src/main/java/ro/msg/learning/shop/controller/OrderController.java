package ro.msg.learning.shop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.dto.OrderDto;
import ro.msg.learning.shop.model.ProductOrder;
import ro.msg.learning.shop.repository.IProductOrderInterfaceRepository;
import ro.msg.learning.shop.service.OrderService;
import ro.msg.learning.shop.utils.Mapper;

@RestController
public class OrderController {

    private final OrderService orderService;

    private Mapper mapper=new Mapper();

    public OrderController(OrderService orderService)
    {
        this.orderService = orderService;
    }

    @PostMapping("/orders")
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto)
    {
        ProductOrder ordertoAdd = orderService.createOrder(mapper.toProductOrder(orderDto));
        OrderDto order = mapper.toOrderDto(ordertoAdd);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
}
