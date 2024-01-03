package com.sky.tv.comics.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import java.util.Set;
import java.util.UUID;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity(name = "r_comic")
public class Comic extends BaseEntity<UUID> {

  @Column
  private String name;
  @Column
  private String image;
  @Column
  private String description;
  @Column(name = "number_view")
  private int numberView;

  @Column(name = "number_like")
  private int numberLike;
  @Enumerated(EnumType.STRING)
  private ProcessEnum status;
  @Enumerated(EnumType.STRING)
  private SourceEnum source;

  @OneToMany(mappedBy = "comic", targetEntity = Chapter.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Chapter> chapters;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
      name = "r_comic_category",
      joinColumns = @JoinColumn(name = "r_comic"),
      inverseJoinColumns = @JoinColumn(name = "r_category"))
  private Set<Category> categories;

  @OneToMany(mappedBy = "comic", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private Set<ComicAnalysis> comicAnalyses;
}
