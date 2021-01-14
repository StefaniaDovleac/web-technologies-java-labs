package com.unibuc.demo.domain;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class File {
    @NotNull
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
