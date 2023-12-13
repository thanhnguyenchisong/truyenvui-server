package com.sky.tv.comics.repository;

import com.sky.tv.comics.entity.Chapter;
import com.sky.tv.comics.repository.custom.CustomChapterRepo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ChapterRepo extends JpaRepository<Chapter, UUID>, CustomChapterRepo {
}
