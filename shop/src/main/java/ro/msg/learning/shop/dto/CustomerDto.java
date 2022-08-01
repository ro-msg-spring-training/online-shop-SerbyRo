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

    private String username;

    private String password;
    private String firstName;
    private String lastName;
    private String email;

    public CustomerDto(Customer customer){
        customerId = customer.getId();
        firstName = customer.getFirstName();
        lastName = customer.getLastName();
        username = customer.getUsername();
        password = customer.getPassword();
        email = customer.getEmail();
    }
}
