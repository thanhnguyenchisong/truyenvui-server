package com.sky.tv.comics.mapper;

import com.sky.tv.comics.dto.ComicDTO;
import com.sky.tv.comics.entity.Comic;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-12T17:50:27+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (JetBrains s.r.o.)"
)
public class AutoComicMapperImpl implements AutoComicMapper {

    @Override
    public ComicDTO mapToComicDto(Comic comic) {
        if ( comic == null ) {
            return null;
        }

        ComicDTO comicDTO = new ComicDTO();

        comicDTO.setId( comic.getId() );
        comicDTO.setName( comic.getName() );
        comicDTO.setImage( comic.getImage() );
        comicDTO.setDescription( comic.getDescription() );

        return comicDTO;
    }

    @Override
    public Comic mapToComic(ComicDTO comicDTO) {
        if ( comicDTO == null ) {
            return null;
        }

        Comic comic = new Comic();

        comic.setId( comicDTO.getId() );
        comic.setName( comicDTO.getName() );
        comic.setImage( comicDTO.getImage() );
        comic.setDescription( comicDTO.getDescription() );

        return comic;
    }
}
