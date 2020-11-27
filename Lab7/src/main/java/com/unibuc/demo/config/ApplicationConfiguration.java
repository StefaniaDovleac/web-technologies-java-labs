package com.unibuc.demo.config;

import com.unibuc.demo.domain.Directory;
import com.unibuc.demo.dto.DirectoryDTO;
import com.unibuc.demo.mapper.DirectoryMapper;
import com.unibuc.demo.mapper.DirectoryMapperImpl;
import com.unibuc.demo.mapper.FileMapper;
import com.unibuc.demo.mapper.FileMapperImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public DirectoryMapper directoryMapper(){
        return new DirectoryMapperImpl();
    }

    @Bean
    public FileMapper fileMapper(){
        return new FileMapperImpl();
    }
}
