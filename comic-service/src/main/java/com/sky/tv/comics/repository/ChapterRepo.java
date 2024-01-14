package com.sky.tv.comics.repository;

import com.sky.tv.comics.entity.Chapter;
import com.sky.tv.comics.entity.Comic;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChapterRepo extends JpaRepository<Chapter, UUID> {
    List<Chapter> getAllChapterByComic(Comic comic);
}
