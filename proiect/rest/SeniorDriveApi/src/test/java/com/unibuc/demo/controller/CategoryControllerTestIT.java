package com.unibuc.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unibuc.demo.domain.Category;
import com.unibuc.demo.service.CategoryService;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@TestPropertySource(locations = "classpath:application-test.properties")
class CategoryControllerTestIT {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CategoryService categoryService;

    @Test
    void test_create() throws Exception {
        // Arrange
        Category request = Category.builder()
                .id(1)
                .name(RandomStringUtils.randomAlphabetic(30))
                .description(RandomStringUtils.randomAlphabetic(30))
                .build();
        // Act
        mockMvc.perform(post("/products/create")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());

        // Assert
        Category found = categoryService.getById(request.getId());
        assertThat(found).isNotNull();

    }

}