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

//    public CustomerDto addCustomer(CustomerDto customerDto)
//    {
//        Customer customer = mapper.toCustomer(customerDto);
//        return mapper.toCustomerDto(customerInterfaceRepository.save(customer));
//    }
//
//    public CustomerDto findCustomerById(Long customerId) throws ProductException {
//        Optional<CustomerDto> customerDto = customerInterfaceRepository.findById(customerId).map(mapper::toCustomerDto);
//        if (customerDto.isPresent())
//        {
//            return customerDto.get();
//        }
//        else
//        {
//            throw new ProductException("The customer doesn't exist!");
//        }
//    }
//    public List<CustomerDto> getAllCustomers(){
//        return customerInterfaceRepository.findAll()
//                .stream()
//                .map(mapper::toCustomerDto)
//                .collect(Collectors.toList());
//    }
//
//    public void deleteCustomerById(Long customerId) throws ProductException {
//        if (customerInterfaceRepository.existsById(customerId)){
//            customerInterfaceRepository.deleteById(customerId);
//        }
//        else
//        {
//            throw new ProductException("The Product doean't exist!");
//        }
//    }
//
//    public void updateCustomer(Long customerId,CustomerDto customerDto) throws ProductException{
//        customerInterfaceRepository.findById(customerId)
//                .orElseThrow(() ->new ProductException("Invalid categoryId"));
//
//        Customer customer = mapper.toCustomer(customerDto);
//        customer.setId(customerId);
//        customerInterfaceRepository.save(customer);
//    }
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
