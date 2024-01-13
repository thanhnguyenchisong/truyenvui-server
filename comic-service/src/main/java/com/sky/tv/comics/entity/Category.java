package com.sky.tv.comics.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
import java.util.Date;
import java.util.Set;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "r_category")
public class Category extends BaseEntity<String> {

  @Column(name = "description")
  private String description;

  @ManyToMany(mappedBy = "categories", fetch = FetchType.LAZY)
  private Set<Comic> comics;

  @ManyToMany(mappedBy = "categories", fetch = FetchType.LAZY)
  private Set<GroupComic> groupComics;
}
