package com.sky.tv.comics.repository;

import com.sky.tv.comics.entity.Category;
import com.sky.tv.comics.entity.CategoryEnum;
import java.util.List;
import java.util.UUID;
import javax.naming.Name;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, UUID> {
    List<Category> findAllByNameIn(List<CategoryEnum> names);
}
