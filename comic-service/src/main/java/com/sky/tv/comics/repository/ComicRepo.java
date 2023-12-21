package com.sky.tv.comics.repository;

import com.sky.tv.comics.entity.Category;
import com.sky.tv.comics.entity.Comic;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ComicRepo extends JpaRepository<Comic, UUID>, PagingAndSortingRepository<Comic, UUID> {

  List<Comic> findAllByCategory(Category category, Pageable pageable);

}
