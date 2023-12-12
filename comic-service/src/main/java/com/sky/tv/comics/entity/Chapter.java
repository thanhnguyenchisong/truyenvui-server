package com.sky.tv.comics.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "r_chapter")
public class Chapter extends ComicEntity {
    private int number;
    private String name;
    @Column(name = "number_read")
    private String numberRead;
    @Column(name = "content_src")
    private String contentSrc;
    @Column(name = "available_chap_number")
    private Integer availableChapNumber;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "r_comic")
    private Comic comic;
}
