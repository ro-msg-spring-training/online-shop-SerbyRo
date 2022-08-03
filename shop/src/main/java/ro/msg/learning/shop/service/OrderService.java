package ro.msg.learning.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.dto.OrderDetailDto;
import ro.msg.learning.shop.dto.OrderDto;
import ro.msg.learning.shop.dto.StockDto;
import ro.msg.learning.shop.model.*;
import ro.msg.learning.shop.repository.*;
import ro.msg.learning.shop.service.exceptions.NotFoundException;
import ro.msg.learning.shop.utils.Mapper;

import javax.persistence.Entity;
import javax.persistence.criteria.Order;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

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

    @Autowired
    private IProductInterfaceRepository productInterfaceRepository;

    @Autowired
    private ILocationInterfaceRepository locationInterfaceRepository;

    private Mapper mapper = new Mapper();


    public OrderService(IProductOrderInterfaceRepository productOrderInterfaceRepository,
                        LocationStrategy locationStrategy,
                        ICustomerInterfaceRepository customerInterfaceRepository,
                        IStockInterfaceRepository stockInterfaceRepository,
                        IProductOrderDetaiInterfaceRepository orderDetaiInterfaceRepository,
                        IProductInterfaceRepository productInterfaceRepository,
                        ILocationInterfaceRepository locationInterfaceRepository)
    {
        this.productOrderInterfaceRepository = productOrderInterfaceRepository;
        this.locationStrategy = locationStrategy;
        this.customerInterfaceRepository = customerInterfaceRepository;
        this.stockInterfaceRepository = stockInterfaceRepository;
        this.orderDetaiInterfaceRepository=orderDetaiInterfaceRepository;
        this.productInterfaceRepository = productInterfaceRepository;
        this.locationInterfaceRepository=locationInterfaceRepository;
    }

    private Set<ProductOrderDetail> findProductsForOrderDetail(ProductOrder orderSave, List<ProductOrderDetail> productIdAndQuantityList) {
        Set<ProductOrderDetail> orderDetails = new HashSet<>();
        for (ProductOrderDetail productIdAndQuantity : productIdAndQuantityList) {
            if(productInterfaceRepository.existsById(productIdAndQuantity.getProduct().getId())) {
                Product foundProduct = productInterfaceRepository.findById(productIdAndQuantity.getProduct().getId()).get();
                ProductOrderDetail orderDetail = new ProductOrderDetail(orderSave, foundProduct, productIdAndQuantity.getQuantity());
                orderDetail.setOrderId(orderSave.getId());
                orderDetail.setProductId(foundProduct.getId());
                orderDetails.add(orderDetail);
            } else {
                throw new RuntimeException("Product not found");
            }
        }
        return orderDetails;
    }

    private void modifyStocks(List<Stock> stocks, List<ProductOrderDetail> productIdAndQuantityList) {
        for(Stock stock : stocks) {
            Optional<ProductOrderDetail> foundProduct =
                    productIdAndQuantityList.stream()
                            .filter(l-> Objects.equals(l.getProduct().getId(), stock.getProduct().getId())).findFirst();

            foundProduct.ifPresent(orderDetailProductIdAndQuantity -> stock.setQuantity(stock.getQuantity() - orderDetailProductIdAndQuantity.getQuantity()));
            stockInterfaceRepository.save(stock);
        }
    }

    public ProductOrder placeOrder(ProductOrder placedOrder, Long customerID, List<ProductOrderDetail> productIdAndQuantityList) {
        if(customerInterfaceRepository.existsById(customerID)) {
            Customer customer = customerInterfaceRepository.getById(customerID);
            placedOrder.setCustomer(customer);



            //implement strategy location
            List<Stock> foundStocks = locationStrategy.findBestLocation(productIdAndQuantityList);
            Location location = locationInterfaceRepository.getById(foundStocks.get(0).getLocation().getId());
            placedOrder.setLocation(location);

            ProductOrder orderSaved = productOrderInterfaceRepository.save(placedOrder);

            Set<ProductOrderDetail> orderDetails = findProductsForOrderDetail(orderSaved, productIdAndQuantityList);
            orderSaved.setOrderDetails(orderDetails);
            orderDetaiInterfaceRepository.saveAll(orderDetails);

            //place order
            modifyStocks(foundStocks, productIdAndQuantityList);
            //lacedOrder.getOrderDetails().stream().findFirst().get().setOrderId(prod);
            return productOrderInterfaceRepository.save(orderSaved);
        } else {
            throw new RuntimeException("Customer does not exist");
        }
    }
}
