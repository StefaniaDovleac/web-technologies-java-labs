package com.unibuc.demo.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DirectoryDTO {
    @NotNull
    private Long id;
    @Size(min=1, max=20)
    private String title;
    private Long parentId;
    private List<FileDTO> files;
}
