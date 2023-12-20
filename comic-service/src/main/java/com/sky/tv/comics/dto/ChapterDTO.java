package com.sky.tv.comics.dto;

import java.util.UUID;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ChapterDTO extends BaseDTO {

  UUID id;
  int number;
  String name;
  int viewNumber;
  String avatarSrc;
  int photoNumber;
}
