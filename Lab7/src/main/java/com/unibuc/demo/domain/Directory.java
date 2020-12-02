package com.unibuc.demo.domain;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Directory {
    @NotNull
    private Long id;
    @Size(min=1)
    private String title;
    private Long parentId;
    private Integer maxNumberOfFiles;
    private List<File> files;
}
