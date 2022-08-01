package ro.msg.learning.shop.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.msg.learning.shop.model.Location;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.model.Stock;

import java.util.List;
import java.util.Optional;

@Repository
public interface IStockInterfaceRepository extends JpaRepository<Stock,Long> {
    List<Stock> findAllByQuantityAndProductId(Integer quantity, Long productId);

    Stock findByProductAndLocation(Product product, Location location);

    List<Stock> findByLocationId(Long locationId);
}
