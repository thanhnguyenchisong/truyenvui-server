package com.sky.tv.comics.repository;

import com.sky.tv.comics.entity.Category;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, String> {

}
