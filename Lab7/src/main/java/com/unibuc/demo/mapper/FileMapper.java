package com.unibuc.demo.mapper;

import com.unibuc.demo.domain.File;
import com.unibuc.demo.dto.FileDTO;
import org.mapstruct.Mapper;

@Mapper (componentModel = "spring")
public interface FileMapper extends EntityMapper<FileDTO, File> {

    File mapToEntity(FileDTO fileDTO);

    FileDTO mapToDTO(File file);
}
