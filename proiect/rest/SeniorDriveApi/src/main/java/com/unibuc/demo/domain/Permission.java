package com.unibuc.demo.domain;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Permission {
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
