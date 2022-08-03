package ro.msg.learning.shop.controller.mappers;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dto.CustomerDto;
import ro.msg.learning.shop.model.Customer;

@Component
public class CustomerMapper {
    public Customer toCustomerEntity(CustomerDto customerDto)
    {
        return Customer.builder()
                .id(customerDto.getCustomerId())
                .firstName(customerDto.getFirstName())
                .lastName(customerDto.getLastName())
                .username(customerDto.getUsername())
                .password(customerDto.getPassword())
                .email(customerDto.getEmail())
                .build();
    }
    public CustomerDto toCustomerDto(Customer customer)
    {
        return CustomerDto.builder()
                .customerId(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .username(customer.getUsername())
                .password(customer.getPassword())
                .email(customer.getEmail())
                .build();
    }
}
