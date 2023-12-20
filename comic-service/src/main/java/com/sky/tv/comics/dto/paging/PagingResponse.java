package com.sky.tv.comics.dto.paging;

import java.util.List;
import lombok.Data;

@Data
public class PagingResponse<T> {

  int pageNumber;
  int pageSize;
  int maxPage;
  int contentSize;
  List<T> content;
}
