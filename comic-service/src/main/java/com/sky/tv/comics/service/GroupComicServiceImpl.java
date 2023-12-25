package com.sky.tv.comics.service;

import com.sky.tv.comics.dto.GroupComicDTO;
import com.sky.tv.comics.entity.GroupComic;
import com.sky.tv.comics.exception.ComicServiceBusinessException;
import com.sky.tv.comics.mapper.AutoGroupMapper;
import com.sky.tv.comics.repository.GroupComicRepo;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class GroupComicServiceImpl implements GroupComicService {

    private final GroupComicRepo groupComicRepo;

    @Override
    public List<GroupComicDTO> getAll() {
        return groupComicRepo.findAll().stream().map(AutoGroupMapper.MAPPER::mapToGroupComicDto).toList();
    }

    @Override
    public void create(List<GroupComicDTO> groupComicDTOs) {
        List<GroupComic> categories = groupComicDTOs.stream().map(AutoGroupMapper.MAPPER::mapToGroupComic).toList();
        groupComicRepo.saveAll(categories);
    }

    @Override
    public void update(List<GroupComicDTO> categoryDTOs) throws ComicServiceBusinessException {
        List<GroupComic> groupComics = categoryDTOs.stream().map(AutoGroupMapper.MAPPER::mapToGroupComic).toList();
        List<GroupComic> resultFromDB = groupComicRepo.findAllById(groupComics.stream().map(GroupComic::getId).toList());
        if(resultFromDB.size() != groupComics.size()) throw new ComicServiceBusinessException("Can't found out ids in DB to update, please recheck");
        groupComicRepo.saveAll(groupComics);
    }
}
