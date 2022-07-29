package ro.msg.learning.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.msg.learning.shop.model.Customer;

import java.util.Optional;

@Repository
public interface ICustomerInterfaceRepository extends JpaRepository<Customer,Long> {
    Optional<Customer> findCustomerByUsername(String username);
}
