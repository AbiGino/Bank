package com.example.bankApplication.Controller;

import com.example.bankApplication.Common.APIResponse;
import com.example.bankApplication.DTO.LoginRequestDTO;
import com.example.bankApplication.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customer")
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<APIResponse> login(@RequestBody LoginRequestDTO loginRequestDTO){
        return loginService.login(loginRequestDTO);
    }
}
