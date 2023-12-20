package com.sky.tv.comics.exception;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ErrorDetails {

  private LocalDateTime timestamp;
  private String message;
  private String path;
  private String errorCode;
}
