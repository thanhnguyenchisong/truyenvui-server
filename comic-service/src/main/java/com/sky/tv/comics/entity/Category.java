package com.sky.tv.comics.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "r_category")
public class Category extends ComicEntity{
    private String name;
    private String description;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "r_comic_type",
            joinColumns = @JoinColumn(name = "r_category"),
            inverseJoinColumns = @JoinColumn(name = "r_comic"))
    private Set<Comic> comics;
}
