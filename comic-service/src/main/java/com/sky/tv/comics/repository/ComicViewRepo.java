package com.sky.tv.comics.repository;

import com.sky.tv.comics.entity.Comic;
import com.sky.tv.comics.repository.custom.CustomComicViewRepo;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComicViewRepo extends JpaRepository<UUID, Comic>, CustomComicViewRepo {

}
