package com.honeymoney.service.user.impl;

import com.honeymoney.dao.UserRepository;
import com.honeymoney.model.UserEntity;
import com.honeymoney.model.UserRole;
import com.honeymoney.service.dto.UserDto;
import com.honeymoney.service.security.SecurityService;
import com.honeymoney.service.user.UserService;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource(name = "userRepository")
    private UserRepository userRepository;
    @Resource(name = "securityService")
    private SecurityService securityService;
    @Resource(name = "conversionService")
    private ConversionService conversionService;
    @Resource(name = "passwordEncoder")
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDto getCurrentUser() {

        UserEntity userEntity = securityService.getCurrentUserEntity();

        if (userEntity != null) {
            return conversionService.convert(userEntity, UserDto.class);
        }
        return null;
    }

}
