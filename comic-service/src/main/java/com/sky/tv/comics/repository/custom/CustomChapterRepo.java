package com.sky.tv.comics.repository.custom;

import com.sky.tv.comics.entity.Chapter;

import java.util.List;
import java.util.UUID;

public interface CustomChapterRepo {
    List<Chapter> getAllChapterByComicId(UUID comicID);
}
