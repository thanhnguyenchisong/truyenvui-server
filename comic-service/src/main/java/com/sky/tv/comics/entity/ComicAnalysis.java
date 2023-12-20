package com.sky.tv.comics.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ComicAnalysis extends BaseEntity {

  @Column(name = "number_read")
  private int numberRead;
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
  private String periodType;

}
