package com.postgrado.ecommerce.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PageDto<T> {
    private List<T> content;
    private boolean last;
    private int pageNumber;
    private int pageSize;
    private int totalPages;
    private long totalElements;
}
