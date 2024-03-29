package com.sky.tv.comics.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.util.Date;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "r_comic_analysis")
public class ComicAnalysis extends UUIDEntity{

  @Column(name = "number_view")
  private int numberView;
  @Column(name = "number_like")
  private int numberLike;
  @Column(name = "start_time")
  private Date startTime;
  @Column(name = "end_time")
  private Date endTime;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "r_comic")
  private Comic comic;
  @Column(name = "period_type")
  @Enumerated(EnumType.STRING)
  private PeriodTypeEnum periodType;

}
