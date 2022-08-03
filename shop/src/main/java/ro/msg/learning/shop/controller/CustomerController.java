package ro.msg.learning.shop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.dto.CustomerDto;
import ro.msg.learning.shop.dto.SupplierDto;
import ro.msg.learning.shop.service.CustomerService;
import ro.msg.learning.shop.service.exceptions.ProductException;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService)
    {
        this.customerService = customerService;
    }

//    @GetMapping("/customers")
//    @ResponseBody
//    public List<CustomerDto> getAllCustomers(){
//        return customerService.getAllCustomers();
//    }
//
//    @GetMapping("/customers/{customerId}")
//    public ResponseEntity<CustomerDto> findCustomerById(@PathVariable Long customerId)
//    {
//        try{
//            CustomerDto customerDto = customerService.findCustomerById(customerId);
//            return ResponseEntity.status(HttpStatus.OK).body(customerDto);
//        }catch (ProductException ex)
//        {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//        }
//    }
//
//    @PostMapping("/customers")
//    public CustomerDto addCustomer(@RequestBody CustomerDto customerDto, HttpServletResponse response)
//    {
//        return customerService.addCustomer(customerDto);
//    }
//
//    @PutMapping("/customers/{customerId}")
//    public void updateSupplier(@PathVariable Long customerId,@RequestBody CustomerDto customerDto) throws ProductException {
//        customerService.updateCustomer(customerId,customerDto);
//    }
//
//    @DeleteMapping("/customers/{customerId}")
//    public ResponseEntity<String> deleteCustomer(@PathVariable Long customerId)
//    {
//        try{
//            customerService.deleteCustomerById(customerId);
//            return ResponseEntity.status(HttpStatus.OK).body("Customer deleted successfully!");
//        }catch (ProductException ex)
//        {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
//        }
//    }
}
