package com.example.bankApplication.Service;

import com.example.bankApplication.Common.APIResponse;
import com.example.bankApplication.DTO.LoginRequestDTO;
import com.example.bankApplication.Entity.Customer;
import com.example.bankApplication.Repository.CustomerRepository;
import com.example.bankApplication.Utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginService {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    APIResponse apiResponse;
    @Autowired
    JwtUtils jwtUtils;

    public ResponseEntity<APIResponse> login(LoginRequestDTO loginRequestDTO) {
        ResponseEntity<APIResponse> apiResponseResponseEntity;
        Customer customer = customerRepository.findByNameAndPassword(loginRequestDTO.getName(), loginRequestDTO.getPassword());
        if (customer != null) {
            String token = jwtUtils.generateJwt(customer);
            Map<String, Object> data = new HashMap<>();
            data.put("Token", token);
            apiResponse.setStatus(200);
            apiResponse.setData(data);
            apiResponse.setMessage("Logged in Successfully");
            apiResponseResponseEntity = new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } else {
            apiResponse.setStatus(400);
            apiResponse.setData(null);
            apiResponse.setMessage("Invalid username or password");
            apiResponseResponseEntity = new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }
        return apiResponseResponseEntity;
    }

}
