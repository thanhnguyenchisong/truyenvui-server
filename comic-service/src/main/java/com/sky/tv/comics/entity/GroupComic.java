package com.sky.tv.comics.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import java.util.Set;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "r_group")
public class GroupComic extends BaseEntity {

  @Column(name = "group_name")
  @Enumerated(EnumType.STRING)
  private GroupEnum groupName;

  @Column(name = "description")
  private String description;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
      name = "r_group_category",
      joinColumns = @JoinColumn(name = "r_category"),
      inverseJoinColumns = @JoinColumn(name = "r_comic"))
  private Set<Category> categories;
}
