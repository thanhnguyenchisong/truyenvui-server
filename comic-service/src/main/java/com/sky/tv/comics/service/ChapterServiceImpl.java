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
import com.sky.tv.comics.mapper.AutoComicMapper;
import com.sky.tv.comics.repository.ChapterRepo;
import com.sky.tv.comics.repository.ComicRepo;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;
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
    List<Comic> comics =
        comicRepo.findAllById(chapterDTOs.stream().map(ChapterDTO::getComicID).toList());
    Map<UUID, Comic> mapComic = comics.stream().collect(Collectors.toMap(Comic::getId,
        Function.identity()));
    List<Chapter> chapters =
        chapterDTOs.stream().map(dto -> {
          Chapter chapter = AutoChapterMapper.MAPPER.toEntity(dto);
          Comic comic = mapComic.get(dto.getComicID());
          if(comic == null) throw new ResourceNotFoundException("Comic", "comic id",
              dto.getComicID().toString());
          chapter.setComic(comic);
          return chapter;
        }).toList();
    chapterRepo.saveAll(chapters);
  }

  @Override
  public void update(List<ChapterDTO> chapterDTOs) throws ComicServiceBusinessException {
    Map<UUID, ChapterDTO> map = chapterDTOs.stream()
        .collect(Collectors.toMap(ChapterDTO::getId, Function.identity()));
    List<Chapter> existingChapter = chapterRepo.findAllById(map.keySet());
    if (chapterDTOs.size() != existingChapter.size()) {
      throw new ComicServiceBusinessException("Can't found out object from id inputs");
    }
    List<Chapter> updatedChapters =
        existingChapter.stream()
            .map(chapter -> AutoChapterMapper.MAPPER.toEntity(map.get(chapter.getId()),
                chapter)).toList();
    chapterRepo.saveAll(updatedChapters);
  }
}
