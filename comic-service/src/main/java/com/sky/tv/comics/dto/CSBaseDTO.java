package com.sky.tv.comics.dto;

import java.util.UUID;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CSBaseDTO extends BaseDTO {
    private UUID id;
}
