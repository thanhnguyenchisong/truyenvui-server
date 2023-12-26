package com.sky.tv.comics.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "r_category")
public class Category extends BaseEntity {
  @Id
  private String name;
  private String description;


  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
      name = "r_comic_category",
      joinColumns = @JoinColumn(name = "r_category"),
      inverseJoinColumns = @JoinColumn(name = "r_comic"))
  private Set<Comic> comics;

  @ManyToMany(mappedBy = "categories", fetch = FetchType.LAZY)
  private Set<GroupComic> groupComics;
}
