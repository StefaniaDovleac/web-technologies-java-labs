package com.unibuc.demo.mapper;

import com.unibuc.demo.domain.Category;
import com.unibuc.demo.dto.CategoryDTO;
import com.unibuc.demo.dto.FileDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = CategoryDTO.class)
public interface CategoryMapper extends EntityMapper<CategoryDTO, Category> {
    public Category mapToEntity(CategoryDTO categoryDTO);

    public CategoryDTO mapToDTO(Category category);
}
