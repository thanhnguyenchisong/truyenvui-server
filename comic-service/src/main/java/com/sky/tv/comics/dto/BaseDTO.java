package com.sky.tv.comics.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseDTO {
  private Date createTime;
  private Date updateTime;
}
