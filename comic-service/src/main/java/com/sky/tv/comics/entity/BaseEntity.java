package com.sky.tv.comics.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@MappedSuperclass
@Data
public class BaseEntity {
  @Column(name = "create_time")
  private Date createTime;
  @Column(name = "update_time")
  private Date updateTime;

  @PrePersist
  public void prePersist() {
    if(createTime == null) createTime = new Date();
  }
}
