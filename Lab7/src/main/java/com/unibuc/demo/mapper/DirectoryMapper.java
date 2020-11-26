package com.unibuc.demo.mapper;

import com.unibuc.demo.domain.Directory;
import com.unibuc.demo.dto.DirectoryDTO;
import org.mapstruct.Mapper;

@Mapper
public interface DirectoryMapper {
    Directory mapFromDTO(DirectoryDTO directoryDTO);

    DirectoryDTO mapToDTO(Directory directory);
}
