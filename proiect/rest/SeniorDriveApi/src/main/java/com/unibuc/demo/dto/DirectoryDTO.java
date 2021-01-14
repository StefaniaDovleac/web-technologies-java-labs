package com.unibuc.demo.dto;

import lombok.*;

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
    @Size(min=1, max=20)
    private String title;
    private Integer parentId;
    private Integer createdBy;
    private Date createdOn;
    private Integer lastModifiedBy;
    private Date lastModifiedOn;
    private Integer categoryId;
}
