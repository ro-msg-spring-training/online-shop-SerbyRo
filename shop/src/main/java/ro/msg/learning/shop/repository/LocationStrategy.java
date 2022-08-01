package ro.msg.learning.shop.repository;

import org.springframework.stereotype.Repository;
import ro.msg.learning.shop.model.ProductOrder;
import ro.msg.learning.shop.model.Stock;

import java.util.List;

@Repository
public interface LocationStrategy {
    public List<Stock> findBestLocation(ProductOrder order);
}
