package ro.msg.learning.shop.dto.savedtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ro.msg.learning.shop.model.Customer;

import java.util.ArrayList;

@Data
@Builder
@AllArgsConstructor
public class SaveCustomerDto {
    private final String firstName;
    private final String lastName;
    private final String username;
    private final String password;
    private final String email;

    public Customer toCustomer(){
        return new Customer(null,firstName,lastName,username,password,email,new ArrayList<>());
    }
}
