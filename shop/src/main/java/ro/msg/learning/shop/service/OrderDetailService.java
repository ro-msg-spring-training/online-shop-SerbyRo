package ro.msg.learning.shop.service;

import org.springframework.stereotype.Service;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.model.ProductOrderDetail;
import ro.msg.learning.shop.repository.IProductOrderDetaiInterfaceRepository;

@Service
public class OrderDetailService {

    private IProductOrderDetaiInterfaceRepository productOrderDetaiInterfaceRepository;

    public OrderDetailService(IProductOrderDetaiInterfaceRepository productOrderDetaiInterfaceRepository)
    {
        this.productOrderDetaiInterfaceRepository = productOrderDetaiInterfaceRepository;
    }


    public void saveOrderDetail(ProductOrderDetail productOrderDetail){
        productOrderDetaiInterfaceRepository.save(productOrderDetail);
    }

    public void deleteOrderDetailById(Long orderDetailId)
    {
        if (productOrderDetaiInterfaceRepository.existsById(orderDetailId))
        {
            productOrderDetaiInterfaceRepository.deleteById(orderDetailId);
        }
    }

}
