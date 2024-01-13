package com.sky.tv.comics.exception;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Data
public class ErrorDetails {

  private LocalDateTime timestamp;
  private int status;
  private String error;
  private String path;
}
