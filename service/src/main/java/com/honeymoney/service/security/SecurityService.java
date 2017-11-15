package com.honeymoney.service.security;

import com.honeymoney.model.UserEntity;

public interface SecurityService {

    String getCurrentUserLogin();
    UserEntity getCurrentUserEntity();
}
