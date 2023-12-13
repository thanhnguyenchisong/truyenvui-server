package com.sky.tv.comics.dto;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class BaseDTO {
    UUID id;
    Date createTimestamp;
    Date updateTimestamp;
}
