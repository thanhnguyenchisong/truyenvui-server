package com.sky.tv.comics.mapper;

import com.sky.tv.comics.dto.ChapterDTO;
import com.sky.tv.comics.entity.Chapter;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * That should have same field name for default mapping. In the case different in field name we have
 * {@link org.mapstruct.Mapping} for that method - with define source name and target name. That
 * will process with that annotation configuration
 */
@Mapper
public interface AutoChapterMapper {

  AutoChapterMapper MAPPER = Mappers.getMapper(AutoChapterMapper.class);

  ChapterDTO mapToChapterDto(Chapter chapter);

  Chapter mapToChapter(ChapterDTO comicDTO);
}
