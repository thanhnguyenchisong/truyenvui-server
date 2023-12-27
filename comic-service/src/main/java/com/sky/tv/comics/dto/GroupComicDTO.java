package com.sky.tv.comics.dto;

import java.util.Set;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class GroupComicDTO extends CategoryDTO {
    private Set<String> categoryIDs;
}