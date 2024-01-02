package com.sky.tv.comics.entity;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import java.util.Date;
import java.util.UUID;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@Getter
@Setter
@MappedSuperclass
public class UUIDBaseEntity extends BaseEntity {
    @Id
    protected UUID id;

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
        return new EqualsBuilder().append(getId(), ((UUIDBaseEntity) obj).getId()).isEquals();
    }

    @Override
    @PrePersist
    public void prePersist() {
        if(id == null) id = UUID.randomUUID();
        if(getCreateTime() == null) setCreateTime(new Date());
    }
}
