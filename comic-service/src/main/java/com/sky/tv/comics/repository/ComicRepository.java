package com.sky.tv.comics.repository;

import com.sky.tv.comics.entity.Comic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface ComicRepository extends JpaRepository<Comic, UUID>, PagingAndSortingRepository<Comic, UUID> {
	String RESOURCE = "Comic";
}
