package com.unibuc.demo.dto;

import lombok.*;

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
    @Size(min=1, max=255)
    private String title;
    private Integer size;
    private Integer parentId;
    private Integer createdBy;
    private Date createdOn;
    private Integer lastModifiedBy;
    private Date lastModifiedOn;
    private String content;
    private Boolean isPublic;
    private String fileUri;
//    private Boolean isFavorite;

}
