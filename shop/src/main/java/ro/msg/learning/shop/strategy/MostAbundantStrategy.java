package ro.msg.learning.shop.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import ro.msg.learning.shop.dto.OrderDetailDto;
import ro.msg.learning.shop.model.ProductOrder;
import ro.msg.learning.shop.model.ProductOrderDetail;
import ro.msg.learning.shop.model.Stock;
import ro.msg.learning.shop.repository.ICustomerInterfaceRepository;
import ro.msg.learning.shop.repository.IProductInterfaceRepository;
import ro.msg.learning.shop.repository.IStockInterfaceRepository;
import ro.msg.learning.shop.repository.LocationStrategy;
import ro.msg.learning.shop.service.StockService;
import ro.msg.learning.shop.service.exceptions.ProductException;

import java.util.*;
import java.util.stream.Collectors;
public class MostAbundantStrategy implements LocationStrategy{

    private final IStockInterfaceRepository stockRepository;

    public MostAbundantStrategy(IStockInterfaceRepository stockRepository) {
        this.stockRepository = stockRepository;
    }


    public List<Stock> findBestLocation(List<ProductOrderDetail> productIdAndQuantityList) {
        List<Stock> stocks = new ArrayList<>();
        for(ProductOrderDetail productIdAndQuantity : productIdAndQuantityList) {
            List<Stock> foundStocks = stockRepository.findByProductId(productIdAndQuantity.getProduct().getId());
            if(foundStocks.isEmpty()) {
                throw new RuntimeException("Not found product in stock");
            }
            Optional<Stock> stock = foundStocks.stream()
                    .filter(s -> s.getQuantity() >= productIdAndQuantity.getQuantity())
                    .max(Comparator.comparing(Stock::getQuantity));
            if(!stock.isPresent()) {
                throw new RuntimeException("Quantity not enough");
            }
            stocks.add(stock.get());
        }
        return stocks;
    }
}