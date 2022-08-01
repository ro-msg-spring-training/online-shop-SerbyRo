package ro.msg.learning.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.model.ProductOrder;
import ro.msg.learning.shop.model.ProductOrderDetail;
import ro.msg.learning.shop.model.Stock;
import ro.msg.learning.shop.repository.ICustomerInterfaceRepository;
import ro.msg.learning.shop.repository.IProductOrderDetaiInterfaceRepository;
import ro.msg.learning.shop.repository.IProductOrderInterfaceRepository;
import ro.msg.learning.shop.repository.IStockInterfaceRepository;
import ro.msg.learning.shop.repository.LocationStrategy;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private IProductOrderInterfaceRepository productOrderInterfaceRepository;
    @Autowired
    private LocationStrategy locationStrategy;

    @Autowired
    private ICustomerInterfaceRepository customerInterfaceRepository;

    @Autowired
    private IStockInterfaceRepository stockInterfaceRepository;

    @Autowired
    private IProductOrderDetaiInterfaceRepository orderDetaiInterfaceRepository;

    public OrderService(IProductOrderInterfaceRepository productOrderInterfaceRepository,
                        LocationStrategy locationStrategy,
                        ICustomerInterfaceRepository customerInterfaceRepository,
                        IStockInterfaceRepository stockInterfaceRepository,
                        IProductOrderDetaiInterfaceRepository orderDetaiInterfaceRepository)
    {
        this.productOrderInterfaceRepository = productOrderInterfaceRepository;
        this.locationStrategy = locationStrategy;
        this.customerInterfaceRepository = customerInterfaceRepository;
        this.stockInterfaceRepository = stockInterfaceRepository;
        this.orderDetaiInterfaceRepository=orderDetaiInterfaceRepository;
    }

    public ProductOrder createOrder(ProductOrder productOrder)
    {
        List<Stock> stockList = locationStrategy.findBestLocation(productOrder);
        try{
            productOrder.setLocation(stockList.get(0).getLocation());
            productOrder.setCreatedAt(LocalDate.now());
            productOrder.setCustomer(customerInterfaceRepository.findAll().get(0));
        }catch (IndexOutOfBoundsException ex)
        {
            throw new IndexOutOfBoundsException("We don't have any products in stock right now!");
        }
        productOrderInterfaceRepository.save(productOrder);

        List<ProductOrderDetail> orderDetails = productOrder.getOrderDetails();
        stockList.forEach(stock->{
            for (ProductOrderDetail orderDetail: orderDetails){
                if (orderDetail.getOrderId() == (stock.getProduct().getId())){
                    int quantity = stock.getQuantity() - orderDetail.getQuantity();
                    Stock stockToUpdate = stockInterfaceRepository.findByProductAndLocation(stock.getProduct(),stock.getLocation());
                    stockToUpdate.setQuantity(quantity);
                    stockInterfaceRepository.save(stockToUpdate);
                    orderDetaiInterfaceRepository.save(orderDetail);
                }
            }
        });
        return productOrder;
    }
}
