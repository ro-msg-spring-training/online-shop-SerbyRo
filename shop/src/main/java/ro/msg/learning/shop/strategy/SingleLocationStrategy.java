package ro.msg.learning.shop.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import ro.msg.learning.shop.dto.OrderDetailDto;
import ro.msg.learning.shop.model.Location;
import ro.msg.learning.shop.model.ProductOrder;
import ro.msg.learning.shop.model.ProductOrderDetail;
import ro.msg.learning.shop.model.Stock;
import ro.msg.learning.shop.repository.*;
import ro.msg.learning.shop.service.exceptions.LocationNotFound;
import ro.msg.learning.shop.service.exceptions.ProductException;

import java.util.*;
import java.util.stream.Collectors;

public class SingleLocationStrategy implements LocationStrategy{

    private final IStockInterfaceRepository stockRepository;
    private final ILocationInterfaceRepository locationRepository;

    public SingleLocationStrategy(IStockInterfaceRepository stockRepository, ILocationInterfaceRepository locationRepository) {
        this.stockRepository = stockRepository;
        this.locationRepository = locationRepository;
    }

    private boolean stockHasNeededProduct(Stock stock,
                                          List<ProductOrderDetail> productIdAndQuantityList ) {

        List<ProductOrderDetail> found = productIdAndQuantityList.stream().filter(p -> stock.getProduct().getId() == p.getProduct().getId() &&
                stock.getQuantity() > p.getQuantity()).collect(Collectors.toList());
        return !found.isEmpty();
    }


    public List<Stock> findBestLocation(List<ProductOrderDetail> productIdAndQuantityList) {

        List<Location> locations = locationRepository.findAll();
        for(Location location : locations) {
            List<Stock> stocksByLocation = stockRepository.findByLocationId(location.getId());
            List<Stock> foundStocksForSingleLocation =
                    stocksByLocation.stream()
                            .filter(stock -> stockHasNeededProduct(stock, productIdAndQuantityList))
                            .collect(Collectors.toList());
            if(foundStocksForSingleLocation.size() == productIdAndQuantityList.size()) {
                return foundStocksForSingleLocation;
            }
        }
        throw new LocationNotFound("Unable to find products in stock");
    }
}
