package com.sky.tv.comics.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import java.util.List;
import java.util.Set;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "r_group")
public class GroupComic extends NameEntity {

  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinTable(
      name = "r_group_category",
      joinColumns = @JoinColumn(name = "r_group"),
      inverseJoinColumns = @JoinColumn(name = "r_category")
  )
  private Set<Category> categories;
}
