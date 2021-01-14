package com.unibuc.demo.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PermissionDTO {
//    @NotNull
    private Integer id;
    @NotNull
    private Integer userId;
    private Boolean createRight;
    private Boolean deleteRight;
    private Boolean renameRight;
    private Boolean moveRight;
    private Boolean uploadRight;
    private Boolean downloadRight;
}
