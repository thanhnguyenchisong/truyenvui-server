package com.sky.tv.comics.entity;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import java.util.Date;
import java.util.UUID;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@MappedSuperclass
public class CSBaseEntity extends BaseEntity {
    @Id
    protected UUID id;

    @Override
    @PrePersist
    public void prePersist() {
        if(id == null) id = UUID.randomUUID();
        if(createTime == null) createTime = new Date();
    }
}
