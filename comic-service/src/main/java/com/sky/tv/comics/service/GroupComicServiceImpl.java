package com.sky.tv.comics.service;

import com.sky.tv.comics.dto.GroupComicDTO;
import com.sky.tv.comics.entity.Category;
import com.sky.tv.comics.entity.GroupComic;
import com.sky.tv.comics.exception.BusinessException;
import com.sky.tv.comics.mapper.AutoGroupComicMapper;
import com.sky.tv.comics.repository.CategoryRepo;
import com.sky.tv.comics.repository.GroupComicRepo;
import com.sky.tv.comics.service.validation.CrudBusinessValidator;
import jakarta.transaction.Transactional;
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
@Transactional
public class GroupComicServiceImpl implements GroupComicService {

    private final GroupComicRepo groupComicRepo;
    private final CategoryRepo categoryRepo;
    private final CrudBusinessValidator crudValidator;

    @Override
    public List<GroupComicDTO> getAll() {
        return groupComicRepo.findAll().stream().map(AutoGroupComicMapper.MAPPER::toDTO).toList();
    }

    @Override
    public void create(List<GroupComicDTO> groupComicDTOs) throws BusinessException {
        validExistingObject(groupComicDTOs);
        Map<String, Category> mapRelation = getRelation(groupComicDTOs);
        Set<GroupComic> groupComics = getGroupComics(groupComicDTOs, mapRelation);
        groupComicRepo.saveAll(groupComics);
    }

    private void validExistingObject(List<GroupComicDTO> groupComicDTOs) {
        Set<String> names = groupComicDTOs.stream().map(GroupComicDTO::getName).collect(Collectors.toSet());
        List<GroupComic> groupComics = groupComicRepo.findAllById(names);
        crudValidator.validate(new HashSet<>(groupComics));
    }

    private Map<String, Category> getRelation(List<GroupComicDTO> groupComicDTOs) {
        Set<String> categorySetInput = new HashSet<>();
        for(GroupComicDTO groupComicDTO : groupComicDTOs) {
            categorySetInput.addAll(groupComicDTO.getCategoryIDs());
        }
        List<Category> categories = categoryRepo.findAllById(categorySetInput);
        crudValidator.validate(categorySetInput, new HashSet<>(categories));
        return categories.stream().collect(Collectors.toMap(Category::getName, Function.identity()));
    }

    private Set<GroupComic> getGroupComics(List<GroupComicDTO> groupComicDTOs, Map<String, Category> mapRelation) {
        return groupComicDTOs.stream().map(groupComicDTO -> {
            GroupComic groupComic = AutoGroupComicMapper.MAPPER.toEntity(groupComicDTO);
            Set<String> categoryIDs = groupComicDTO.getCategoryIDs();
            Set<Category> categories = categoryIDs.stream().map(mapRelation::get).collect(Collectors.toSet());
            groupComic.setCategories(categories);
            return groupComic;
        }).collect(Collectors.toSet());
    }

    /**
     * Support category
     * @param groupComicDTOs
     * @throws BusinessException
     */
    @Override
    public void update(List<GroupComicDTO> groupComicDTOs) throws BusinessException {
        Map<String, Category> mapRelation = getRelation(groupComicDTOs);
        Set<GroupComic> groupComics = getGroupComics(groupComicDTOs, mapRelation);
        groupComicRepo.saveAll(groupComics);
    }
}
