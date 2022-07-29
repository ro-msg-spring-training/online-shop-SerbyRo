package ro.msg.learning.shop.service;


import org.springframework.stereotype.Service;
import ro.msg.learning.shop.dto.CustomerDto;
import ro.msg.learning.shop.dto.savedtos.SaveCustomerDto;
import ro.msg.learning.shop.dto.savedtos.SaveProductDto;
import ro.msg.learning.shop.model.Customer;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.model.ProductCategory;
import ro.msg.learning.shop.model.Supplier;
import ro.msg.learning.shop.repository.ICustomerInterfaceRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private ICustomerInterfaceRepository customerInterfaceRepository;

    public CustomerService(ICustomerInterfaceRepository customerInterfaceRepository)
    {
        this.customerInterfaceRepository=customerInterfaceRepository;
    }

    public Customer addCustomer(SaveCustomerDto saveCustomerDto){
        return customerInterfaceRepository.save(saveCustomerDto.toCustomer());
    }

    public Optional<Customer> findCustomerById(Long customerId){
        return customerInterfaceRepository.findById(customerId);
    }
    public List<Customer> getAllCustomers() {
        return customerInterfaceRepository.findAll();
    }

    @Transactional
    public void deleteCustomerById(Long productId) {
        if (customerInterfaceRepository.existsById(productId)) {
            customerInterfaceRepository.deleteById(productId);
        }
    }
}
