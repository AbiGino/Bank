package com.example.bankApplication.Service;

import com.example.bankApplication.Common.APIResponse;
import com.example.bankApplication.Entity.Customer;
import com.example.bankApplication.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    APIResponse apiResponse;

    public ResponseEntity<APIResponse> getAccountsById(Integer account_number) {
        ResponseEntity<APIResponse> apiResponseResponseEntity;
        Customer customer = customerRepository.findByAccountNumber(account_number);
        if (customer != null) {
            apiResponse.setStatus(200);
            apiResponse.setData(customer);
            apiResponse.setMessage("Done");
            apiResponseResponseEntity = new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } else {
            apiResponse.setStatus(400);
            apiResponse.setData(new ArrayList<>());
            apiResponse.setError("Invalid account number entered!");
            apiResponseResponseEntity = new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }

        return apiResponseResponseEntity;
    }

    public ResponseEntity<APIResponse> createCustomer(Customer customer) {
        ResponseEntity<APIResponse> apiResponseResponseEntity;
        if(customer!=null){
            customerRepository.save(customer);
            apiResponse.setStatus(201);
            apiResponse.setMessage("Customer created Successfully");
            apiResponse.setData(customer);
            apiResponseResponseEntity=new ResponseEntity<>(apiResponse,HttpStatus.CREATED);
        }else{
            apiResponse.setStatus(400);
            apiResponse.setData(new ArrayList<>());
            apiResponse.setError("Invalid account number entered!");
            apiResponseResponseEntity = new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }
        return apiResponseResponseEntity;
    }

    public ResponseEntity<APIResponse> getAll() {
        ResponseEntity<APIResponse> apiResponseResponseEntity;
        List<Customer> customer= customerRepository.findAll();
        if (customer != null) {
            apiResponse.setStatus(200);
            apiResponse.setData(customer);
            apiResponse.setMessage("Done");
            apiResponseResponseEntity = new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } else {
            apiResponse.setStatus(400);
            apiResponse.setData(new ArrayList<>());
            apiResponse.setError("Invalid account number entered!");
            apiResponseResponseEntity = new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }

        return apiResponseResponseEntity;

    }
}
