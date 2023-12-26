package com.sky.tv.comics.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import java.util.Set;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "r_comic")
public class Comic extends CSBaseEntity {

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

  @OneToMany(mappedBy = "comic", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private Set<Chapter> chapters;

  @ManyToMany(mappedBy = "comics", fetch = FetchType.LAZY)
  private Set<Category> categories;

  @OneToMany(mappedBy = "comic", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private Set<ComicAnalysis> comicAnalyses;
}
