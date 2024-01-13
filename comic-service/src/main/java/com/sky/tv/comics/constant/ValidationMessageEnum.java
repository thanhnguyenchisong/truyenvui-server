package com.sky.tv.comics.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ValidationMessageEnum {
  EXIST("Objects exist"),
  NOT_EXIST("Objects don't exist");

  private final String message;
}
