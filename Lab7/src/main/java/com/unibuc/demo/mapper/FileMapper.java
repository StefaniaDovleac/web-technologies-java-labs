package com.unibuc.demo.mapper;

import com.unibuc.demo.domain.File;
import com.unibuc.demo.dto.FileDTO;
import org.mapstruct.Mapper;

@Mapper
public interface FileMapper {

    File mapFromDTO(FileDTO fileDTO);

    FileDTO mapToDTO(File file);
}
