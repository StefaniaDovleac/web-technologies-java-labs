package com.unibuc.lab10.config;

import com.unibuc.lab10.mapper.AddressMapper;
import com.unibuc.lab10.mapper.AddressMapperImpl;
import com.unibuc.lab10.mapper.EmployeeMapper;
import com.unibuc.lab10.mapper.EmployeeMapperImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectConfig {

    @Bean
    public AddressMapper addressMapper() {
        return new AddressMapperImpl();
    }

    @Bean
    public EmployeeMapper employeeMapper() {
        return new EmployeeMapperImpl();
    }
}
