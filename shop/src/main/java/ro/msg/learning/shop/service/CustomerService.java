package ro.msg.learning.shop.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.model.Customer;
import ro.msg.learning.shop.repository.ICustomerInterfaceRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private ICustomerInterfaceRepository customerInterfaceRepository;



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
