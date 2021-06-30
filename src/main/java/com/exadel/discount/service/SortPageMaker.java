package com.exadel.discount.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class SortPageMaker {
    public static Pageable makePageable(int pageNumber, int pageSize, String sortDirection, String sortField) {
    Sort sort = Sort.unsorted();
    if (!sortDirection.equals("")) {
        sort = Sort.by(Sort.Direction.valueOf(sortDirection.toUpperCase()), sortField);
    }
    return PageRequest.of(pageNumber - 1, pageSize, sort);
}
}
