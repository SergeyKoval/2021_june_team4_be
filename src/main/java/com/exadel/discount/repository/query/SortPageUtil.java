package com.exadel.discount.repository.query;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class SortPageUtil {

    private static final String ASCENDING_SORT_DIRECTION = "ASC";
    private static final String DESCENDING_SORT_DIRECTION = "DESC";

    public static Pageable makePageable(int pageNumber, int pageSize, String sortDirection, String sortField) {
        Sort sort = Sort.unsorted();
        if (!ASCENDING_SORT_DIRECTION.equalsIgnoreCase(sortDirection) &&
                !DESCENDING_SORT_DIRECTION.equalsIgnoreCase(sortDirection)) {
            return PageRequest.of(pageNumber, pageSize, sort);
        }
        sort = Sort.by(Sort.Direction.valueOf(sortDirection.toUpperCase()), sortField);
        return PageRequest.of(pageNumber, pageSize, sort);
    }

    public static Sort makeSort(String sortDirection, String sortField) {
        Sort sort = Sort.unsorted();
        if (!ASCENDING_SORT_DIRECTION.equalsIgnoreCase(sortDirection) &&
                !DESCENDING_SORT_DIRECTION.equalsIgnoreCase(sortDirection)) {
            return sort;
        }
        sort = Sort.by(Sort.Direction.valueOf(sortDirection.toUpperCase()), sortField);
        return sort;
    }
}
