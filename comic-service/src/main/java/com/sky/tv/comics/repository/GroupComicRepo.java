package com.sky.tv.comics.repository;

import com.sky.tv.comics.entity.GroupComic;
import com.sky.tv.comics.entity.GroupEnum;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupComicRepo extends JpaRepository<GroupComic, GroupEnum> {
    List<GroupComic> findAllByNameIn(List<GroupEnum> groupEnumDTOS);
}
