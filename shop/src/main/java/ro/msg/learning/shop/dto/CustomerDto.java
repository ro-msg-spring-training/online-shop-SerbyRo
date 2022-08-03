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
}
