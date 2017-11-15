package com.honeymoney.service.conversion;

import com.honeymoney.model.UserEntity;
import com.honeymoney.model.UserRole;
import com.honeymoney.service.dto.UserDto;
import org.springframework.core.convert.converter.Converter;

public class UserDtoToEntityConverter implements Converter<UserDto, UserEntity> {

    @Override
    public UserEntity convert(UserDto userDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setPassword(userDto.getPassword());
        userEntity.setRole(UserRole.USER.toString());
        return userEntity;
    }
}
