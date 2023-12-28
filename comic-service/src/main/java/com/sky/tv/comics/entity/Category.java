package com.sky.tv.comics.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import java.util.List;
import java.util.Set;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "r_category")
public class Category extends NameEntity {

  @ManyToMany(mappedBy = "categories", fetch = FetchType.LAZY)
  private Set<Comic> comics;

  @ManyToMany(mappedBy = "categories", fetch = FetchType.LAZY)
  private Set<GroupComic> groupComics;
}
