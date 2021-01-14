package com.unibuc.demo.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private Integer id;
    private String userName;
    private String email;
    private Boolean isAdmin;
    private String password;
}
