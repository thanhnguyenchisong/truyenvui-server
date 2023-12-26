package com.sky.tv.comics.dto;

import java.util.UUID;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ChapterDTO extends CSBaseDTO {
  private UUID comicID;
  private int number;
  private String name;
  private int viewNumber;
  private String avatarUrl;
  private int photoNumber;
}
