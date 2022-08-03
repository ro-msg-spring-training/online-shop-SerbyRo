package ro.msg.learning.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.dto.LocationDto;
import ro.msg.learning.shop.dto.StockDto;
import ro.msg.learning.shop.model.Location;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.model.Stock;
import ro.msg.learning.shop.repository.ILocationInterfaceRepository;
import ro.msg.learning.shop.repository.IStockInterfaceRepository;
import ro.msg.learning.shop.service.exceptions.NotFoundException;
import ro.msg.learning.shop.service.exceptions.ProductException;
import ro.msg.learning.shop.utils.Mapper;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class StockService {
    @Autowired
    private IStockInterfaceRepository stockInterfaceRepository;
    @Autowired
    private ILocationInterfaceRepository locationInterfaceRepository;

    private Mapper mapper = new Mapper();

    public StockService(IStockInterfaceRepository stockInterfaceRepository,ILocationInterfaceRepository locationInterfaceRepository)
    {
        this.stockInterfaceRepository = stockInterfaceRepository;
        this.locationInterfaceRepository = locationInterfaceRepository;
    }

//    public List<Stock> exportsStocks(Long locationId)
//    {
//        List<Stock> stockList;
//        Optional<Location> location = locationInterfaceRepository.findById(locationId);
//        if (location.isPresent())
//        {
//            stockList = stockInterfaceRepository.findByLocationId(locationId);
//            return stockList;
//        }
//        else
//        {
//            throw new RuntimeException("We can't find the location with id " + locationId);
//        }
//    }
//
//    public void createStock(Stock stock)
//    {
//        stockInterfaceRepository.save(stock);
//    }
//
//    public  void deleteAllStocks(){
//        stockInterfaceRepository.deleteAll();
//    }
//
//    public void updateStock(Stock stockToUpdate, Integer quantityTaken){
//        Integer newQuantity = stockToUpdate.getQuantity()-quantityTaken;
//        stockToUpdate.setQuantity(newQuantity);
//        stockInterfaceRepository.save(stockToUpdate);
//    }
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
