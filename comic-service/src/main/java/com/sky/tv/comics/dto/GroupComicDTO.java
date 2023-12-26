package com.sky.tv.comics.dto;

import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
public class GroupComicDTO extends CategoryDTO {
    private List<String> categoryIDs;
}