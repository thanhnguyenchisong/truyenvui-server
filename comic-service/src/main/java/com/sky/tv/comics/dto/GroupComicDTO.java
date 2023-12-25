package com.sky.tv.comics.dto;

import java.util.List;
import java.util.UUID;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class GroupComicDTO extends BaseDTO {
    private String name;
    private String description;
    private List<UUID> categoryIDs;
}

