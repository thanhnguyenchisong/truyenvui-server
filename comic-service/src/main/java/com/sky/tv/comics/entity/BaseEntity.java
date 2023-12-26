package com.sky.tv.comics.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import java.util.Date;
import lombok.Data;

@MappedSuperclass
@Data
public class BaseEntity {
  @Column(name = "create_time")
  protected Date createTime;
  @Column(name = "update_time")
  protected Date updateTime;

  @PrePersist
  public void prePersist() {
    if(createTime == null) createTime = new Date();
  }
}
