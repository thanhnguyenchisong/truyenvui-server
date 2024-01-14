package com.sky.tv.comics.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@MappedSuperclass
@Getter
@Setter
public class BaseEntity<K> {

  @Id
  protected K id;
  @Column(name = "create_time")
  private Date createTime;
  @Column(name = "update_time")
  private Date updateTime;

  @PrePersist
  public void prePersist() {
    if(createTime == null) createTime = new Date();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 31).append(getId()).toHashCode();
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    return new EqualsBuilder().append(getId(), ((BaseEntity<?>) obj).getId()).isEquals();
  }
  @PreUpdate
  public void preUpdate() {
    updateTime = new Date();
  }
}
