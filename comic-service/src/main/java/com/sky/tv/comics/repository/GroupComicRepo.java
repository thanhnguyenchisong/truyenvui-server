package com.sky.tv.comics.repository;

import com.sky.tv.comics.entity.GroupComic;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupComicRepo extends JpaRepository<GroupComic, String> {

}
