package com.honeymoney.service.signup;

import com.honeymoney.service.signup.dto.SignupDto;
import com.honeymoney.service.signup.exception.LoginAlreadyExistsException;

public interface SignupService {
    void signUp(SignupDto signupDto) throws LoginAlreadyExistsException;
}
