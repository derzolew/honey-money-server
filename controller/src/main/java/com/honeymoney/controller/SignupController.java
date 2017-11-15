package com.honeymoney.controller;

import com.honeymoney.service.signup.SignupService;
import com.honeymoney.service.signup.dto.SignupDto;
import com.honeymoney.service.signup.exception.LoginAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/signup")
public class SignupController {

    @Resource(name = "signupService")
    private SignupService signupService;

    @PostMapping
    public ResponseEntity signUp(@RequestBody SignupDto signupDto) {
        try {
            signupService.signUp(signupDto);
            return new ResponseEntity(HttpStatus.OK);
        } catch (LoginAlreadyExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
