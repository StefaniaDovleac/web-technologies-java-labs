package com.unibuc.demo.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileDTO {
    //    @NotNull
    private Integer id;
    @Size(min = 1, max = 255)
    @NotBlank
    private String title;
    private Integer size;
    private Integer parentId;
    @NotNull
    private Integer createdBy;
    @NotNull
    private Date createdOn;
    @NotNull
    private Integer lastModifiedBy;
    @NotNull
    private Date lastModifiedOn;
    @Size(max = 255)
    private String content;
    private Boolean isPublic;
    @Size(max = 255)
    private String fileUri;
}
