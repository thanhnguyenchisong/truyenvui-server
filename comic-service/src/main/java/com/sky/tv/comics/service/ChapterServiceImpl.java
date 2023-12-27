package com.sky.tv.comics.service;

import com.sky.tv.comics.constant.ValidationMessageEnum;
import com.sky.tv.comics.dto.ChapterDTO;
import com.sky.tv.comics.entity.Chapter;
import com.sky.tv.comics.entity.Comic;
import com.sky.tv.comics.exception.BusinessException;
import com.sky.tv.comics.exception.ResourceNotFoundException;
import com.sky.tv.comics.mapper.AutoChapterMapper;
import com.sky.tv.comics.repository.ChapterRepo;
import com.sky.tv.comics.repository.ComicRepo;
import com.sky.tv.comics.service.validation.CrudBusinessValidator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ChapterServiceImpl implements ChapterService {

  private final ChapterRepo chapterRepo;
  private final ComicRepo comicRepo;
  private final CrudBusinessValidator validator;

  @Override
  public List<ChapterDTO> getAllByComic(UUID comicID) {
    Comic comic = comicRepo.findById(comicID).orElseThrow(
        () -> new ResourceNotFoundException("Comic", "id", comicID.toString())
    );
    return comic.getChapters().stream().map(AutoChapterMapper.MAPPER::toDTO).toList();
  }

  @Override
  public ChapterDTO get(UUID id) {
    Chapter chapter = chapterRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Chapter", "id", id.toString()));
    return AutoChapterMapper.MAPPER.toDTO(chapter);
  }

  @Override
  public List<ChapterDTO> get(List<UUID> ids) {
    return chapterRepo.findAllById(ids).stream().map(AutoChapterMapper.MAPPER::toDTO).toList();
  }

  @Override
  public void create(List<ChapterDTO> chapterDTOs) {
    Map<UUID, Comic> map = getRelation(chapterDTOs);
    Set<Chapter> chapters = getEntity(chapterDTOs, map);
    chapterRepo.saveAll(chapters);
  }

  private Set<Chapter> getEntity(List<ChapterDTO> chapterDTOs, Map<UUID, Comic> map) {
    return chapterDTOs.stream().map(dto -> {
      Chapter chapter = AutoChapterMapper.MAPPER.toEntity(dto);
      Comic comic = map.get(dto.getComicID());
      chapter.setComic(comic);
      return chapter;
    }).collect(Collectors.toSet());
  }

  private Map<UUID, Comic> getRelation(List<ChapterDTO> chapterDTOs) {
    Set<UUID> comicIDs = chapterDTOs.stream().map(ChapterDTO::getComicID).collect(Collectors.toSet());
    Set<Comic> existingComics = new HashSet<>(comicRepo.findAllById(comicIDs));
    validator.validate(
        () -> comicIDs.size() == existingComics.size(),
        "Can't find out Comic relation object");
    return existingComics.stream().collect(Collectors.toMap(Comic::getId, Function.identity()));
  }

  @Override
  public void update(List<ChapterDTO> chapterDTOs) throws BusinessException {
    Map<UUID, ChapterDTO> map = chapterDTOs.stream().collect(
        Collectors.toMap(ChapterDTO::getId, Function.identity())
    );
    List<Chapter> existingChapter = chapterRepo.findAllById(map.keySet());
    validator.validate(
        () -> map.size() == existingChapter.size(),
        ValidationMessageEnum.NOT_EXIST.getMessage()
    );
    List<Chapter> updatingChapters = existingChapter.stream()
        .map(
            chapter -> AutoChapterMapper.MAPPER.toEntity(map.get(chapter.getId()), chapter)
        ).toList();
    chapterRepo.saveAll(updatingChapters);
  }
}
