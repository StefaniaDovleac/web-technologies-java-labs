package com.unibuc.demo.mapper;

import com.unibuc.demo.domain.FileHistory;
import com.unibuc.demo.dto.FileHistoryDTO;
import org.mapstruct.Mapper;

@Mapper (componentModel = "spring")
public interface FileHistoryMapper extends EntityMapper<FileHistoryDTO, FileHistory> {

    FileHistory mapToEntity(FileHistoryDTO fileDTO);

    FileHistoryDTO mapToDTO(FileHistory file);
}
