package com.unibuc.demo.controller;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

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
//                .header("{\"key\":\"Authorization\",\"value\":\"Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZCIsImV4cCI6MTYxMDQyMzM1NSwiaWF0IjoxNjEwMzg3MzU1fQ.qkc-KayZvj-eqvUBGqjjsrbx18c0TqCrytfPI_h9NJY\",\"description\":\"\",\"type\":\"text\",\"enabled\":true}]")
//                .contentType("application/json")
//                .content(objectMapper.writeValueAsString(request)))
//                .andExpect(status().isCreated());
//
////Assert
//
//    }
}