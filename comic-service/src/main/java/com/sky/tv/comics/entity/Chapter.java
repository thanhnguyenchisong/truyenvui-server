package com.sky.tv.comics.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "r_chapter")
public class Chapter extends BaseEntity {

  private int number;
  private String name;
  @Column(name = "view_number")
  private int viewNumber;
  @Column(name = "avatar_src")
  private String avatar_src;
  @Column(name = "photo_number")
  private int photoNumber;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "r_comic")
  private Comic comic;
}