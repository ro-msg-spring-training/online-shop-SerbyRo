package ro.msg.learning.shop.service;

import org.springframework.stereotype.Service;
import ro.msg.learning.shop.repository.IProductOrderInterfaceRepository;

@Service
public class OrderService {

    private IProductOrderInterfaceRepository productOrderInterfaceRepository;

    public OrderService(IProductOrderInterfaceRepository productOrderInterfaceRepository)
    {
        this.productOrderInterfaceRepository = productOrderInterfaceRepository;
    }
}
