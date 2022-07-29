package ro.msg.learning.shop.service;

import org.springframework.stereotype.Service;
import ro.msg.learning.shop.model.Location;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.model.Stock;
import ro.msg.learning.shop.repository.IStockInterfaceRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class StockService {

    private IStockInterfaceRepository stockInterfaceRepository;

    public StockService(IStockInterfaceRepository stockInterfaceRepository)
    {
        this.stockInterfaceRepository = stockInterfaceRepository;
    }

    public Optional<Stock> findStockByLocationAndProduct(Location location, Product product) {
        return stockInterfaceRepository.findStockByLocationAndProduct(location, product);
    }

    public void addStock(Stock stock) {
       stockInterfaceRepository.save(stock);
    }

    public Collection<Stock> findAllStocks() {
        return stockInterfaceRepository.findAll();
    }

    public void deleteAllStocks() {
        stockInterfaceRepository.deleteAll();
    }

}
