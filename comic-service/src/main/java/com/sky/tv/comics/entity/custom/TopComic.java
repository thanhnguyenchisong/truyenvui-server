package com.sky.tv.comics.entity.custom;

import com.sky.tv.comics.entity.Comic;
import lombok.Data;

@Data
public class TopComic {
    private int numberView;
    private int numberLike;
    private Comic comic;
}
