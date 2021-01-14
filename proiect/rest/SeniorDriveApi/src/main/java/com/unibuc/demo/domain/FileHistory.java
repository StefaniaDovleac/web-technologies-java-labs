package com.unibuc.demo.domain;

import com.unibuc.demo.enums.ActionType;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileHistory {
    @NotNull
    private Integer id;
    @NotNull
    private Integer fileId;
    @NotNull
    private String title;
    private Integer size;
    private Integer parentId;
    @NotNull
    private Integer modifiedBy;
    @NotNull
    private Date modifiedOn;
    private String fileUri;
    @NotNull
    private Integer revision;
    @NotNull
    private ActionType action;
}
