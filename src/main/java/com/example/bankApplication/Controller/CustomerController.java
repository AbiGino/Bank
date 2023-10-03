package com.example.bankApplication.Controller;

import com.example.bankApplication.Common.APIResponse;
import com.example.bankApplication.Entity.Customer;
import com.example.bankApplication.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/createCustomer")
    ResponseEntity<APIResponse> createCustomer(@Validated @RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }

    @GetMapping("/getcustomer/{account_number}")
    ResponseEntity<APIResponse> getAccountsById(@Validated @PathVariable Integer account_number) {
        return customerService.getAccountsById(account_number);
    }

    @GetMapping("/getallcustomer")
    ResponseEntity<APIResponse> getAll() {
        return customerService.getAll();
    }

}
