package ro.msg.learning.shop.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import ro.msg.learning.shop.model.ProductOrder;
import ro.msg.learning.shop.model.Stock;
import ro.msg.learning.shop.repository.IStockInterfaceRepository;
import ro.msg.learning.shop.repository.LocationStrategy;
import ro.msg.learning.shop.service.exceptions.ProductException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SingleLocationStrategy implements LocationStrategy {

    @Autowired
    private IStockInterfaceRepository stockInterfaceRepository;

    @Override
    public List<Stock> findBestLocation(ProductOrder order) {
        Map<Long, List<Stock>> locations = new HashMap<>();
        order.getOrderDetails().forEach(orderDetail->{
            List<Stock> stockList = stockInterfaceRepository.findAllByQuantityAndProductId(orderDetail.getQuantity(),orderDetail.getProduct().getId());
            if (stockList.isEmpty())
            {
                try {
                    throw new ProductException("Nu exista produsul cu detaliile cu id-ul "+ orderDetail.getProductId());
                } catch (ProductException e) {
                    throw new RuntimeException(e);
                }
            }
            stockList.forEach(stock -> {
                List<Stock> locationList = locations.get(stock.getLocation().getId());
                if (locationList == null)
                {
                    locationList = new ArrayList<>();
                }
                locationList.add(new Stock(stock.getLocation(),stock.getProduct(),stock.getQuantity()));
                locations.put(stock.getLocation().getId(),locationList);
            });
        });

        for (Map.Entry<Long,List<Stock>> entry: locations.entrySet())
        {
            if (entry.getValue().size() == order.getOrderDetails().size())
            {
                return entry.getValue();
            }
        }
        return new ArrayList<>();
    }
}
