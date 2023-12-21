package com.sky.tv.comics.repository.custom;

import com.sky.tv.comics.entity.Chapter;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomChapterRepoImpl implements CustomChapterRepo {

/*  @Autowired
  private EntityManager entityManager;*/

  public List<Chapter> getAllChapterByComicId(UUID comicID) {
   /* String formQuery = "SELECT c FROM Chapter c WHERE c.comic.id = %s";
    String query = String.format(formQuery, comicID.toString());
    return entityManager.createQuery(query, Chapter.class).getResultList();
*/
    return null;
  }
}
