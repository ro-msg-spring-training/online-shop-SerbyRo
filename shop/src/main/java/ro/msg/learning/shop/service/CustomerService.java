package ro.msg.learning.shop.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.dto.CustomerDto;
import ro.msg.learning.shop.dto.ProductCategoryDto;
import ro.msg.learning.shop.model.Customer;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.model.ProductCategory;
import ro.msg.learning.shop.model.Supplier;
import ro.msg.learning.shop.repository.ICustomerInterfaceRepository;
import ro.msg.learning.shop.service.exceptions.NotFoundException;
import ro.msg.learning.shop.service.exceptions.ProductException;
import ro.msg.learning.shop.utils.Mapper;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    @Autowired
    private ICustomerInterfaceRepository customerInterfaceRepository;

    private Mapper mapper = new Mapper();

    public CustomerService(ICustomerInterfaceRepository customerInterfaceRepository)
    {
        this.customerInterfaceRepository=customerInterfaceRepository;
    }
    public Customer saveCustomer(Customer customer)
    {
        return customerInterfaceRepository.save(customer);
    }
    public Optional<Customer> findCustomerById(Long customerId)
    {
        return customerInterfaceRepository.findById(customerId);
    }

    public List<Customer> findAllCustomers()
    {
        return customerInterfaceRepository.findAll();
    }

    public void deleteCustomerById(Long customerId)
    {
        if (customerInterfaceRepository.existsById(customerId))
        {
            customerInterfaceRepository.deleteById(customerId);
        }
    }

}
