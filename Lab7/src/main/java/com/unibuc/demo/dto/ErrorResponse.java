package com.unibuc.demo.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ErrorResponse {
    private Integer code;
    private String message;
}
