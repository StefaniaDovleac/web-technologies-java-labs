package com.unibuc.demo.mapper;

import com.unibuc.demo.domain.Directory;
import com.unibuc.demo.dto.DirectoryDTO;
import com.unibuc.demo.dto.FileDTO;
import org.mapstruct.Mapper;

@Mapper (componentModel = "spring", uses = FileDTO.class)
public interface DirectoryMapper extends EntityMapper<DirectoryDTO, Directory>{
    Directory mapToEntity(DirectoryDTO directoryDTO);

    DirectoryDTO mapToDTO(Directory directory);
}
