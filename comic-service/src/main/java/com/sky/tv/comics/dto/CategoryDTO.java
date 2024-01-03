package com.sky.tv.comics.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@EqualsAndHashCode(callSuper = true)
@Data
public class CategoryDTO extends BaseDTO<String> {
  private String description;
}
