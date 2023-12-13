package com.sky.tv.comics.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
public class ChapterDTO extends BaseDTO {
    UUID id;
    int number;
    String name;
    int viewNumber;
    String avatarSrc;
    int photoNumber;
}
