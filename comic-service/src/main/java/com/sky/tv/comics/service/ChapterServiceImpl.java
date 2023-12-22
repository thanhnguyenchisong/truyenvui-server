package com.sky.tv.comics.service;

import com.sky.tv.comics.dto.ChapterDTO;
import com.sky.tv.comics.entity.Category;
import com.sky.tv.comics.entity.Chapter;
import com.sky.tv.comics.exception.ComicServiceBusinessException;
import com.sky.tv.comics.mapper.AutoCategoryMapper;
import com.sky.tv.comics.mapper.AutoChapterMapper;
import com.sky.tv.comics.repository.ChapterRepo;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChapterServiceImpl implements ChapterService {

  @Autowired
  private ChapterRepo chapterRepo;

  @Override
  public List<ChapterDTO> getAllChapter(UUID comicID) {
    return chapterRepo.getAllChapterByComicId(comicID).stream()
        .map(AutoChapterMapper.MAPPER::mapToChapterDto).toList();
  }

  @Override
  public void createChapters(List<ChapterDTO> chapterDTOs) {
    List<Chapter> categories = chapterDTOs.stream().map(AutoChapterMapper.MAPPER::mapToChapter).toList();
    chapterRepo.saveAll(categories);
  }

  @Override
  public void updateChapters(List<ChapterDTO> chapterDTOs) throws ComicServiceBusinessException {
    List<Chapter> resultFromDB = chapterRepo.findAllById(chapterDTOs.stream().map(ChapterDTO::getId).toList());
    if(chapterDTOs.size() != resultFromDB.size()) throw new ComicServiceBusinessException("ThaNHNCS");
    List<Chapter> categories = chapterDTOs.stream().map(AutoChapterMapper.MAPPER::mapToChapter).toList();
    chapterRepo.saveAll(categories);
  }
}
