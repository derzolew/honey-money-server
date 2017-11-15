package com.honeymoney.service.security.impl;

import com.honeymoney.dao.UserRepository;
import com.honeymoney.model.UserEntity;
import com.honeymoney.service.security.SecurityService;
import com.honeymoney.service.user.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("securityService")
public class SecurityServiceImpl implements SecurityService {

    @Resource(name = "userRepository")
    private UserRepository userRepository;

    @Override
    public String getCurrentUserLogin()
    {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        String userName = null;
        if (authentication != null)
        {
            if (authentication.getPrincipal() instanceof UserDetails)
            {
                UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
                userName = springSecurityUser.getUsername();
            }
            else if (authentication.getPrincipal() instanceof String)
            {
                userName = (String) authentication.getPrincipal();
            }
        }
        return userName;
    }

    @Override
    public UserEntity getCurrentUserEntity()
    {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        UserEntity user;
        if (authentication != null)
        {
            if (authentication.getPrincipal() instanceof CustomUserDetails)
            {
                CustomUserDetails springSecurityUser = (CustomUserDetails) authentication.getPrincipal();
                user = springSecurityUser.getUserEntity();
                return userRepository.findOne(user.getId());
            }
            else if (authentication.getPrincipal() instanceof String)
            {
                return null;
            }
        }
        return null;
    }
}
