package com.honeymoney.service.conversion;

import com.honeymoney.model.UserEntity;
import com.honeymoney.model.UserRole;
import com.honeymoney.service.signup.dto.SignupDto;
import org.springframework.core.convert.converter.Converter;

public class SignupDtoToUserEntityConverter implements Converter<SignupDto, UserEntity> {

    @Override
    public UserEntity convert(SignupDto signupDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setLogin(signupDto.getLogin());
        userEntity.setPassword(signupDto.getPassword());
        userEntity.setRole(UserRole.USER.toString());
        return userEntity;
    }
}
