package com.sky.tv.comics.repository;

import com.sky.tv.comics.entity.Comic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ComicRepository extends JpaRepository<Comic, UUID> {
	public static final String RESOURCE = "Comic";
}
