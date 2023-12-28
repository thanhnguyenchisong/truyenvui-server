package com.sky.tv.comics.entity;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import java.util.Date;
import java.util.UUID;
import lombok.Data;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@Data
@MappedSuperclass
public class UUIDBaseEntity extends BaseEntity {
    @Id
    protected UUID id;

    @Override
    public boolean equals(final Object obj) {
        boolean result;
        result = (obj != null) && (obj.getClass() == getClass()) && (obj == this);

        return result && new EqualsBuilder().append(id, ((UUIDBaseEntity) obj).getId()).isEquals();
    }

    @Override
    @PrePersist
    public void prePersist() {
        if(id == null) id = UUID.randomUUID();
        if(getCreateTime() == null) setCreateTime(new Date());
    }
}
