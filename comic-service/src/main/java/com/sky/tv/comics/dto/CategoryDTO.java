package com.sky.tv.comics.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
public class CategoryDTO extends BaseDTO {

  String name;
  String description;
}
