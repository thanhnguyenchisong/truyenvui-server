package com.sky.tv.comics.dto.response;

import com.sky.tv.comics.dto.PageDTO;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagingResponse<T> extends PageDTO {
  int contentSize;
  List<T> content;
}
