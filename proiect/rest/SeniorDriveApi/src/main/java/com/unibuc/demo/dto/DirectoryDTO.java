package com.unibuc.demo.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DirectoryDTO {
    //    @NotNull
    private Integer id;
    @Size(min = 1, max = 255)
    private String title;
    private Integer parentId;
    @Null
    private Integer createdBy;
    @Null
    private Date createdOn;
    @Null
    private Integer lastModifiedBy;
    @Null
    private Date lastModifiedOn;
    private Integer categoryId;
}
