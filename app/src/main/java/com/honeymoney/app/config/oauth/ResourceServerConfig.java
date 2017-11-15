package com.honeymoney.app.config.oauth;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

import javax.annotation.Resource;

@Configuration
@EnableResourceServer
class ResourceServerConfig extends ResourceServerConfigurerAdapter
{
    @Resource(name = "tokenStore")
    private TokenStore tokenStore;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources)
    {
        resources.tokenStore(tokenStore);
    }

    @Override
    public void configure(HttpSecurity http)
    {
        try
        {
            http
                    .authorizeRequests()
                    .antMatchers("/").permitAll()
                    .antMatchers("/*.js").permitAll()
                    .antMatchers("/*.css").permitAll()
                    .antMatchers("/*.html").permitAll()
                    .antMatchers("/*.json").permitAll()
                    .antMatchers("/signup/**").permitAll()
                    .antMatchers("/swagger-ui.html").permitAll()
                    .antMatchers("/swagger-resources/configuration/ui").permitAll()
                    .antMatchers("/ swagger-resources/configuration/security").permitAll()
                    .anyRequest().hasAnyRole("USER", "ADMIN");
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}