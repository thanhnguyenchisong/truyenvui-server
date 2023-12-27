package com.sky.tv.comics.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import java.util.Set;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "r_category")
public class Category extends BaseEntity {
  @Id
  private String name;
  private String description;

  @ManyToMany(mappedBy = "categories", fetch = FetchType.LAZY)
  private Set<Comic> comics;

  @ManyToMany(mappedBy = "categories", fetch = FetchType.LAZY)
  private Set<GroupComic> groupComics;
}
