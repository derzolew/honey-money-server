package com.honeymoney.service.user.impl;

import com.honeymoney.dao.UserRepository;
import com.honeymoney.model.UserEntity;
import com.honeymoney.service.user.CustomUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService
{

    @Resource(name = "userRepository")
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException
    {
        UserEntity user = userRepository.findByLogin(login);
        return new CustomUserDetails(user);
    }
}
