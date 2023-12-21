package com.sky.tv.comics.dto.request;

import com.sky.tv.comics.dto.PageDTO;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class GetComicPaging extends PageDTO {
  private List<String> names;
  private GetTypeDTO typeDTO;
}
