package com.unibuc.demo.mapper;

import com.unibuc.demo.domain.Permission;
import com.unibuc.demo.dto.CategoryDTO;
import com.unibuc.demo.dto.PermissionDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = CategoryDTO.class)
public interface PermissionMapper extends EntityMapper<PermissionDTO, Permission> {
    public Permission mapToEntity(PermissionDTO permissionDTO);

    public PermissionDTO mapToDTO(Permission permission);
}
