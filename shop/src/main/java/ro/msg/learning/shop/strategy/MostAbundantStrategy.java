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

//public class MostAbundantStrategy implements LocationStrategy {
//    @Autowired
//    private IStockInterfaceRepository stockInterfaceRepository;
//
//    @Autowired
//    private StockService stockService;
//
//    @Override
//    public List<Stock> findBestLocation(ProductOrder order) {
//        List<Stock> stockLocation = new ArrayList<>();
//        order.getOrderDetails().forEach(orderDetail -> {
//           try {
//                List<Stock> stocks = stockInterfaceRepository.findAllByQuantityAndProductId(orderDetail.getQuantity(), orderDetail.getProduct().getId());
//                if (stocks.isEmpty()) {
//                    try {
//                        throw new ProductException("Nu exista produsul cu detaliile cu id-ul " + orderDetail.getProductId());
//                    } catch (ProductException e) {
//                        throw new RuntimeException(e);
//                    }
//                }
////                Stock stock = stocks.get(0);
////                stockLocation.add(new Stock(stock.getLocation(), stock.getProduct(), stock.getQuantity()));
//                Stock mostAbundant = stocks.stream().max(Comparator.comparing(Stock::getQuantity)).get();
//                if (orderDetail.getQuantity() <= mostAbundant.getQuantity())
//                {
//                    stockLocation.add(mostAbundant);
//                    stockService.updateStock(mostAbundant, orderDetail.getQuantity());
////                    if (stockLocation.size() == order.getOrderDetails().size())
////                        return stockLocation;
//                }
//            } catch (Exception ex) {
//                throw new RuntimeException("The product with the id " + orderDetail.getProduct().getId() + " doesn't exist!");
//            }
//        });
//        return stockLocation;
//    }
//
//
//
//}

public class MostAbundantStrategy implements LocationStrategy{

    private final IStockInterfaceRepository stockRepository;

    public MostAbundantStrategy(IStockInterfaceRepository stockRepository) {
        this.stockRepository = stockRepository;
    }


    public List<Stock> findBestLocation(List<ProductOrderDetail> productIdAndQuantityList) {
        List<Stock> stocks = new ArrayList<>();
        for(ProductOrderDetail productIdAndQuantity : productIdAndQuantityList) {
            List<Stock> foundStocks = stockRepository.findByProductId(productIdAndQuantity.getProductId());
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