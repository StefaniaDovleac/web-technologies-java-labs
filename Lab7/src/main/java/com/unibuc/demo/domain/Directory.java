package com.unibuc.demo.domain;

import lombok.*;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Directory {
    private Long id;
    private String title;
    private Long parentId;
    private List<File> files;
}
