package com.sky.tv.comics.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

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
