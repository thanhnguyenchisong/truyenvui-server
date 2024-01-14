package com.sky.tv.comics.dto.request;

import com.sky.tv.comics.dto.PageDTO;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class GetComicByCategory extends PageDTO {

  List<String> categories;
}
