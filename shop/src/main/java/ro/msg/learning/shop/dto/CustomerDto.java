package ro.msg.learning.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ro.msg.learning.shop.model.Customer;

@Data
@Builder
@AllArgsConstructor
public class CustomerDto {
    private Long customerId;

    private String firstName;
    private String lastName;
    private String email;

    public CustomerDto(Customer customer){
        customerId = customer.getCustomerId();
        firstName = customer.getFirstName();
        lastName = customer.getLastName();
        email = customer.getEmail();
    }
}
