package com.sky.tv.comics.dto.request;

import com.sky.tv.comics.dto.paging.PagingRequest;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class GetComicByCategory extends PagingRequest {

  List<String> categories;
}
