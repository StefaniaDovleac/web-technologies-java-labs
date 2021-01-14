package com.unibuc.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unibuc.demo.auth.MyUserDetailsService;
import com.unibuc.demo.domain.Category;
import com.unibuc.demo.dto.CategoryDTO;
import com.unibuc.demo.mapper.CategoryMapper;
import com.unibuc.demo.service.CategoryService;
import com.unibuc.demo.util.JwtUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CategoryController.class)
class CategoryControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @MockBean
//    private CategoryService service;
//
//    @MockBean
//    private CategoryMapper mapper;
//
//    @MockBean
//    private MyUserDetailsService myUserDetailsService;
//
//    @MockBean
//    private JwtUtil jwtUtil;
//
//    @Test
//    void test_create() throws Exception {
//        // Arrange
//        CategoryDTO request = CategoryDTO.builder()
//                .name(RandomStringUtils.randomAlphabetic(30))
//                .description(RandomStringUtils.randomAlphabetic(30))
//                .build();
//
//        Category created = Category.builder()
//                .id(1)
//                .name(RandomStringUtils.randomAlphabetic(30))
//                .description(RandomStringUtils.randomAlphabetic(30))
//                .build();
//
//        when(service.save(any(Category.class))).thenReturn(created);
//
//        // Act
//        mockMvc.perform(post("/categories/create")
//                .header("{\"key\":\"Authorization\",\"value\":\"Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZCIsImV4cCI6MTYxMDY3NzQ5MCwiaWF0IjoxNjEwNjQxNDkwfQ.s6KaUYRc7ZfdV8bMrd6jzMK0eQp_oZrTjIl0qR_-gfQ\",\"description\":\"\",\"type\":\"text\",\"enabled\":true}]")
//                .contentType("application/json")
//                .content(objectMapper.writeValueAsString(request)))
//                .andExpect(status().isCreated());
//
////Assert
//
//    }
}