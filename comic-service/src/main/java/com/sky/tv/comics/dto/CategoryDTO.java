package com.sky.tv.comics.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@EqualsAndHashCode(callSuper = true)
@ToString
@Data
public class CategoryDTO extends BaseDTO {
  private String name;
  private String description;
}
