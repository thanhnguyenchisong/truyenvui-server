package com.sky.tv.comics.repository;

import com.sky.tv.comics.entity.GroupEnum;
import com.sky.tv.comics.entity.GroupComic;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupComicRepo extends JpaRepository<GroupComic, UUID> {
    List<GroupComic> findAllByNameIn(List<GroupEnum> groupEnumDTOS);
}
