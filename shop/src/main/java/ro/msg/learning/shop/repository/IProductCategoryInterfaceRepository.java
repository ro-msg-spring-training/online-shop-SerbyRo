package ro.msg.learning.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.msg.learning.shop.model.Location;
import ro.msg.learning.shop.model.ProductCategory;

import java.util.Optional;

@Repository
public interface IProductCategoryInterfaceRepository extends JpaRepository<ProductCategory,Long> {
    Optional<ProductCategory> findProductCategoryByName(String name);
}
