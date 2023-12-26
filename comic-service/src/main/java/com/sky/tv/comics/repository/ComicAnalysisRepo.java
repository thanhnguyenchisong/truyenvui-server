package com.sky.tv.comics.repository;

import com.sky.tv.comics.entity.Comic;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComicAnalysisRepo extends JpaRepository<Comic, UUID> {

}
