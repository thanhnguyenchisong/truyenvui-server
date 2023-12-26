package com.sky.tv.comics.service;

import com.sky.tv.comics.dto.ChapterDTO;
import com.sky.tv.comics.dto.ComicDTO;
import com.sky.tv.comics.entity.Category;
import com.sky.tv.comics.entity.Chapter;
import com.sky.tv.comics.entity.Comic;
import com.sky.tv.comics.exception.ComicServiceBusinessException;
import com.sky.tv.comics.exception.ResourceNotFoundException;
import com.sky.tv.comics.mapper.AutoCategoryMapper;
import com.sky.tv.comics.mapper.AutoChapterMapper;
import com.sky.tv.comics.repository.ChapterRepo;
import com.sky.tv.comics.repository.ComicRepo;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ChapterServiceImpl implements ChapterService {

  private final ChapterRepo chapterRepo;
  private final ComicRepo comicRepo;

  @Override
  public List<ChapterDTO> getAll(UUID comicID) {
    Comic comic = comicRepo.findById(comicID).orElseThrow(() -> new ResourceNotFoundException("Comic", "id", comicID.toString()));
    return comic.getChapters().stream()
        .map(AutoChapterMapper.MAPPER::toDTO).toList();
  }

  @Override
  public void create(List<ChapterDTO> chapterDTOs) {
    List<Chapter> chapters = chapterDTOs.stream().map(AutoChapterMapper.MAPPER::toEntity).toList();
    chapterRepo.saveAll(chapters);
  }

  @Override
  public void update(List<ChapterDTO> chapterDTOs) throws ComicServiceBusinessException {
    List<Chapter> resultFromDB = chapterRepo.findAllById(chapterDTOs.stream().map(ChapterDTO::getId).toList());
    if(chapterDTOs.size() != resultFromDB.size()) throw new ComicServiceBusinessException("Can't found out object from id inputs");
    List<Chapter> chapters = chapterDTOs.stream().map(AutoChapterMapper.MAPPER::toEntity).toList();
    chapterRepo.saveAll(chapters);
  }
}
