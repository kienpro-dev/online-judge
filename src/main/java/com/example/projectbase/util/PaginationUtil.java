package com.example.projectbase.util;

import com.example.projectbase.constant.CommonConstant;
import com.example.projectbase.constant.SortByDataConstant;
import com.example.projectbase.domain.dto.pagination.PaginationRequestDto;
import com.example.projectbase.domain.dto.pagination.PaginationSortRequestDto;
import com.example.projectbase.domain.dto.pagination.PagingMeta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PaginationUtil {

  public static Pageable buildPageable(PaginationRequestDto request) {
    return PageRequest.of(request.getPageNum(), request.getPageSize());
  }

  public static Pageable buildPageable(PaginationSortRequestDto request, SortByDataConstant constant) {
    Sort sort;
    if(request.getIsAscending()){
      sort = Sort.by(request.getSortBy(constant)).ascending();
    } else {
      sort = Sort.by(request.getSortBy(constant)).descending();
    }
    return PageRequest.of(request.getPageNum(), request.getPageSize(), sort);
  }

  public static <T> PagingMeta buildPagingMeta(PaginationRequestDto request, Page<T> pages) {
    return new PagingMeta(
        pages.getTotalElements(),
        pages.getTotalPages(),
        request.getPageNum() + CommonConstant.ONE_INT_VALUE,
        request.getPageSize(),
        CommonConstant.EMPTY_STRING,
        CommonConstant.EMPTY_STRING
    );
  }

  public static <T> PagingMeta buildPagingMeta(PaginationSortRequestDto request, SortByDataConstant constant, Page<T> pages) {
    return new PagingMeta(
        pages.getTotalElements(),
        pages.getTotalPages(),
        request.getPageNum() + CommonConstant.ONE_INT_VALUE,
        request.getPageSize(),
        request.getSortBy(constant),
        request.getIsAscending().equals(Boolean.TRUE) ? CommonConstant.SORT_TYPE_ASC : CommonConstant.SORT_TYPE_DESC
    );
  }

}
