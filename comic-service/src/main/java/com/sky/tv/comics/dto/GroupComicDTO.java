package com.sky.tv.comics.dto;

import java.util.Set;
import java.util.UUID;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString
@Data
public class GroupComicDTO extends CategoryDTO {
    private Set<String> categoryIDs;
}