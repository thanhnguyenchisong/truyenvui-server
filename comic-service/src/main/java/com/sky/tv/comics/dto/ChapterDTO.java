package com.sky.tv.comics.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ChapterDTO extends CSBaseDTO {
  private int number;
  private String name;
  private int viewNumber;
  private String avatarUrl;
  private int photoNumber;
}
