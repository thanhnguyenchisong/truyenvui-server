package com.sky.tv.comics.service;

import com.sky.tv.comics.dto.ChapterDTO;
import com.sky.tv.comics.dto.ComicDTO;
import com.sky.tv.comics.exception.ComicBusinessException;
import com.sky.tv.comics.mapper.AutoChapterMapper;
import com.sky.tv.comics.repository.ChapterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ChapterServiceImpl implements ChapterService {
    @Autowired
    ChapterRepo chapterRepo;

    @Override
    public List<ChapterDTO> getAllChapter(UUID comicID) throws ComicBusinessException {
        return chapterRepo.getAllChapterByComicId(comicID).stream().map(AutoChapterMapper.MAPPER::mapToComicDto).toList();
    }
}
