package com.sky.tv.comics.service;

import com.sky.tv.comics.dto.ChapterDTO;
import com.sky.tv.comics.exception.ComicBusinessException;

import java.util.List;
import java.util.UUID;

public interface ChapterService {
    /**
     * Get all chapters by comic ID
     * @param comicID
     * @return
     * @throws ComicBusinessException
     */
    List<ChapterDTO> getAllChapter(UUID comicID) throws ComicBusinessException;
}
