package com.sky.tv.comics.mapper;

import com.sky.tv.comics.dto.ComicDTO;
import com.sky.tv.comics.entity.Comic;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * That should have same field name for default mapping. In the case different in field name we have
 * {@link org.mapstruct.Mapping} for that method - with define source name and target name. That
 * will process with that annotation configuration
 */
@Mapper
public interface AutoComicMapper {
  AutoComicMapper MAPPER = Mappers.getMapper(AutoComicMapper.class);

 /* default ChapterDTO toChapterDto(Chapter chapter) {
    return AutoChapterMapper.MAPPER.mapToChapterDto(chapter);
  }*/

  ComicDTO mapToComicDto(Comic comic);

  Comic mapToComic(ComicDTO comicDTO);
}
