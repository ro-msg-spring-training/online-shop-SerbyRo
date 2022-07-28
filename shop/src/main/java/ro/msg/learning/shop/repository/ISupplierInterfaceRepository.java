package ro.msg.learning.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.msg.learning.shop.model.Supplier;

@Repository
public interface ISupplierInterfaceRepository extends JpaRepository<Supplier,Long> {
}
