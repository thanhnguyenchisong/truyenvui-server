package com.sky.tv.comics.repository.custom;

import com.sky.tv.comics.entity.Chapter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

public class CustomChapterRepoImpl implements CustomChapterRepo {
    @Autowired
    private EntityManager entityManager;

    public List<Chapter> getAllChapterByComicId(UUID comicID) {
  /*      CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Chapter> cr = cb.createQuery(Chapter.class);
        Root<Chapter> root = cr.from(Chapter.class);
        cr.where(cb.equal(root.get("comic").get("id"), comicID));*/
        // another way
        String formQuery = "SELECT c FROM Chapter c WHERE c.comic.id = %s";
        String query = String.format(formQuery, comicID.toString());

        return entityManager.createQuery(query, Chapter.class).getResultList();
    }
}
