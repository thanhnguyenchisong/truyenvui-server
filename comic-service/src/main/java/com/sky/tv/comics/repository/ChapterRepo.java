package com.sky.tv.comics.repository;

import com.sky.tv.comics.entity.Chapter;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChapterRepo extends JpaRepository<Chapter, UUID> {

}
