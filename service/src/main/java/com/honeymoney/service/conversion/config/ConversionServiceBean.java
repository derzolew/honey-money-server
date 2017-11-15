package com.honeymoney.service.conversion.config;

import com.honeymoney.service.conversion.SignupDtoToUserEntityConverter;
import com.honeymoney.service.conversion.UserDtoToEntityConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.converter.Converter;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class ConversionServiceBean {

    @Bean
    public ConversionServiceFactoryBean conversionService()
    {
        ConversionServiceFactoryBean bean = new ConversionServiceFactoryBean();
        Set<Converter> converters = new HashSet<>();
        converters.add(new UserDtoToEntityConverter());
        converters.add(new SignupDtoToUserEntityConverter());
        bean.setConverters(converters);
        return bean;
    }
}
