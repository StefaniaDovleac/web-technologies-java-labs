package com.unibuc.demo.dto;

import com.unibuc.demo.validators.MaxNumberOf;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@MaxNumberOf(message="Length of files is greater than or equal to maxNumberOfFiles ")
public class DirectoryDTO {
//    @NotNull
    private Long id;
    @Size(min=1, max=20)
    private String title;
    private Long parentId;
    private Integer maxNumberOfFiles;
    private List<FileDTO> files;
}
