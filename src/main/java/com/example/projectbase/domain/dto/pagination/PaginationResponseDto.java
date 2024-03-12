package com.example.projectbase.domain.dto.pagination;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Setter
@Getter
public class PaginationResponseDto<T> {

  private PagingMeta meta;

  private List<T> items;

  public PaginationResponseDto(PagingMeta meta, List<T> items) {
    this.meta = meta;

    if (items == null) {
      this.items = null;
    } else {
      this.items = Collections.unmodifiableList(items);
    }
  }

  public List<T> getItems() {
    return items == null ? null : new ArrayList<>(items);
  }

}
