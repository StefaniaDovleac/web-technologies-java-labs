package com.unibuc.demo.mapper;

import java.util.List;

public interface EntityMapper <DTO, Entity> {
    Entity mapToEntity(DTO dto);

    DTO mapToDTO(Entity entity);

    List<Entity> mapToEntity (List<DTO> dtoList);

    List<DTO> mapToDTO(List<Entity> entityList);
}
