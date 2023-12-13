package com.sky.tv.comics.dto.paging;

import lombok.Data;

import java.util.List;

@Data
public class PagingResponse<T> {
    int pageNumber;
    int pageSize;
    int maxPage;
    int contentSize;
    List<T> content;
}
