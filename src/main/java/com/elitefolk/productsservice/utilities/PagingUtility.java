package com.elitefolk.productsservice.utilities;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PagingUtility {

    public static Pageable getPageable(Integer page, Integer size, String sort, String direction) {
        Sort.Direction direct = Sort.Direction.ASC;
        if(direction != null) {
            direction = direction.toUpperCase();
            direct = Sort.Direction.valueOf(direction);
        }
        if(sort == null) {
            sort = "name";
        }
        if(page == null) {
            page = 0;
        }
        if(size == null) {
            size = 100;
        }
        return PageRequest.of(page, size, Sort.by(direct, sort));
    }
}
