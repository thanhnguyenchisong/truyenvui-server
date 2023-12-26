package com.sky.tv.comics.service;

import com.sky.tv.comics.dto.GroupComicDTO;
import com.sky.tv.comics.entity.Category;
import com.sky.tv.comics.entity.GroupComic;
import com.sky.tv.comics.exception.ComicServiceBusinessException;
import com.sky.tv.comics.mapper.AutoGroupComicMapper;
import com.sky.tv.comics.repository.CategoryRepo;
import com.sky.tv.comics.repository.GroupComicRepo;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
@Transactional
public class GroupComicServiceImpl implements GroupComicService {

    private final GroupComicRepo groupComicRepo;
    private final CategoryRepo categoryRepo;
    private final EntityManager entityManager;

    @Override
    public List<GroupComicDTO> getAll() {
        return groupComicRepo.findAll().stream().map(AutoGroupComicMapper.MAPPER::toDTO).toList();
    }

    @Override
    public void create(List<GroupComicDTO> groupComicDTOs) throws ComicServiceBusinessException {
        List<String> names = groupComicDTOs.stream().map(GroupComicDTO::getName).toList();
        List<GroupComic> groupComicFromDB = groupComicRepo.findAllById(names);
        if (!groupComicFromDB.isEmpty()) {
            throw new ComicServiceBusinessException("Object already existed in Database");
        }

        List<GroupComic> groupComics = getComics(groupComicDTOs);

        groupComicRepo.saveAll(groupComics);
    }

    private List<GroupComic> getComics(List<GroupComicDTO> groupComicDTOs) {
        return groupComicDTOs.stream().map(groupComicDTO -> {
            GroupComic groupComic = AutoGroupComicMapper.MAPPER.toEntity(groupComicDTO);
            List<Category> categories = categoryRepo.findAllById(groupComicDTO.getCategoryIDs());
            groupComic.setCategories(new HashSet<>(categories));
            return groupComic;
        }).toList();
    }

    @Override
    public void update(List<GroupComicDTO> groupComicDTOs) throws ComicServiceBusinessException {
        List<GroupComic> groupComics = getComics(groupComicDTOs);
        List<GroupComic> resultFromDB = groupComicRepo.findAllById(groupComics.stream().map(GroupComic::getName).toList());
        if(resultFromDB.size() != groupComics.size()) throw new ComicServiceBusinessException("Can't found out ids in DB to update, please recheck");
        groupComicRepo.saveAll(groupComics);
    }
}
