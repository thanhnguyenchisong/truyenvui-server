package com.sky.tv.comics.dto;

import java.util.Date;
import java.util.UUID;
import lombok.Data;

@Data
public class BaseDTO {

  UUID id;
  Date createTimestamp;
  Date updateTimestamp;
}
