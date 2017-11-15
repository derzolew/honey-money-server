package com.honeymoney.service.signup.impl;

import com.honeymoney.dao.UserRepository;
import com.honeymoney.model.UserEntity;
import com.honeymoney.service.signup.SignupService;
import com.honeymoney.service.signup.dto.SignupDto;
import com.honeymoney.service.signup.exception.LoginAlreadyExistsException;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("signupService")
public class SignupServiceImpl implements SignupService {

    @Resource(name = "userRepository")
    private UserRepository userRepository;
    @Resource(name = "conversionService")
    private ConversionService conversionService;
    @Resource(name = "passwordEncoder")
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void signUp(SignupDto signupDto) throws LoginAlreadyExistsException {
        if (userRepository.findByLogin(signupDto.getLogin()) != null) {
            throw new LoginAlreadyExistsException("Login already exists");
        }
        if (signupDto.getPassword() != null) {
            signupDto.setPassword(passwordEncoder.encode(signupDto.getPassword()));
        }
        UserEntity newUser = conversionService.convert(signupDto, UserEntity.class);
        userRepository.saveAndFlush(newUser);
    }
}
