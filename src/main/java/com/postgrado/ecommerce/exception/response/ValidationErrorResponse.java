package com.postgrado.ecommerce.exception.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ValidationErrorResponse {
    private int code;
    private List<FieldErrorModel> errors;
}
