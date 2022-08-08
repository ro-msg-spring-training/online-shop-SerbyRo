package ro.msg.learning.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.model.ProductOrderDetail;

import java.util.Optional;

@Repository
public interface IProductOrderDetaiInterfaceRepository extends JpaRepository<ProductOrderDetail,Long> {

}
