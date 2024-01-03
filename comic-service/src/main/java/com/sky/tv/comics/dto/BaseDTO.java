package com.sky.tv.comics.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;
import java.util.UUID;
import lombok.Data;
import lombok.ToString;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class BaseDTO<K> {
  private K id;
  private Date createTime;
  private Date updateTime;
}
