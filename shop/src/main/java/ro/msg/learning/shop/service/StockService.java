package ro.msg.learning.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.model.Stock;
import ro.msg.learning.shop.repository.ILocationInterfaceRepository;
import ro.msg.learning.shop.repository.IStockInterfaceRepository;
import ro.msg.learning.shop.service.exceptions.NotFoundException;

import java.util.List;

@Service
public class StockService {
    @Autowired
    private IStockInterfaceRepository stockInterfaceRepository;
    @Autowired
    private ILocationInterfaceRepository locationInterfaceRepository;

    public StockService(IStockInterfaceRepository stockInterfaceRepository,ILocationInterfaceRepository locationInterfaceRepository)
    {
        this.stockInterfaceRepository = stockInterfaceRepository;
        this.locationInterfaceRepository = locationInterfaceRepository;
    }

    public void updateStock(Stock stock)
    {
        if (stockInterfaceRepository.existsById(stock.getLocationId())&&
                stockInterfaceRepository.existsById(stock.getProductId()))
        {
            stockInterfaceRepository.save(stock);
        }else
        {
            throw new NotFoundException("Stock not found!");
        }

    }

    public List<Stock> getAllStocksByProductId(Long productId)
    {
        return stockInterfaceRepository.findByProductId(productId);
    }

    public List<Stock> getAllStocksByLocationId(Long locationId)
    {
        return stockInterfaceRepository.findByLocationId(locationId);
    }
}
