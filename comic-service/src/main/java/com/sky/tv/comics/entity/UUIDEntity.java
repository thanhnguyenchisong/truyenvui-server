package com.sky.tv.comics.entity;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import java.util.UUID;

@MappedSuperclass
public class UUIDEntity extends BaseEntity<UUID> {

    @Override
    @PrePersist
    public void prePersist() {
        if(getId() == null) setId(UUID.randomUUID());
        super.prePersist();
    }
}
