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

}
