package com.sky.tv.comics.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "r_group")
public class GroupComic extends BaseEntity<String> {

  @Column(name = "description")
  private String description;

  @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinTable(
      name = "r_group_category",
      joinColumns = @JoinColumn(name = "r_group"),
      inverseJoinColumns = @JoinColumn(name = "r_category")
  )
  private Set<Category> categories;
}
