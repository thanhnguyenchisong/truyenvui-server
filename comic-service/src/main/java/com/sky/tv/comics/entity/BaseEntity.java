package com.sky.tv.comics.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import java.util.Date;
import java.util.UUID;
import lombok.Data;

@MappedSuperclass
@Data
public class BaseEntity {

  @Id
  private UUID id;
  @Column(name = "create_time")
  private Date createTime;
  @Column(name = "update_time")
  private Date updateTime;
}
