package com.unibuc.demo.domain;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Directory {
    @NotNull
    private Integer id;
    @Size(min=1)
    private String title;
    private Integer parentId;
    private Integer createdBy;
    private Date createdOn;
    private Integer lastModifiedBy;
    private Date lastModifiedOn;
    private Integer categoryId;
}
