package com.sky.tv.comics.dto.request;

import com.sky.tv.comics.dto.paging.PagingRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class GetComicByCategory extends PagingRequest {
    List<String> categories;
}
