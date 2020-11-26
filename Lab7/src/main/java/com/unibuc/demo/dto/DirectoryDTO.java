package com.unibuc.demo.dto;

import lombok.*;

import java.util.List;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DirectoryDTO {
    private Long id;
    private String title;
    private Long parentId;
    private List<FileDTO> files;
}
