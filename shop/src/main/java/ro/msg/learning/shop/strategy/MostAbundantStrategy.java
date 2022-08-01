package ro.msg.learning.shop.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import ro.msg.learning.shop.model.ProductOrder;
import ro.msg.learning.shop.model.Stock;
import ro.msg.learning.shop.repository.IStockInterfaceRepository;
import ro.msg.learning.shop.repository.LocationStrategy;
import ro.msg.learning.shop.service.exceptions.ProductException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MostAbundantStrategy implements LocationStrategy {
    @Autowired
    private IStockInterfaceRepository stockInterfaceRepository;

    @Override
    public List<Stock> findBestLocation(ProductOrder order) {
        List<Stock> stockLocation = new ArrayList<>();
        order.getOrderDetails().forEach(orderDetail -> {
            try {
                List<Stock> stocks = stockInterfaceRepository.findAllByQuantityAndProductId(orderDetail.getQuantity(), orderDetail.getProduct().getId());
                if (stocks.isEmpty()) {
                    try {
                        throw new ProductException("Nu exista produsul cu detaliile cu id-ul " + orderDetail.getProductId());
                    } catch (ProductException e) {
                        throw new RuntimeException(e);
                    }
                }
                Stock stock = stocks.get(0);
                stockLocation.add(new Stock(stock.getLocation(), stock.getProduct(), stock.getQuantity()));
            } catch (Exception ex) {
                throw new RuntimeException("The product with the " + orderDetail.getProduct().getId() + " doesn't exist!");
            }
        });
        return stockLocation;
    }


}
