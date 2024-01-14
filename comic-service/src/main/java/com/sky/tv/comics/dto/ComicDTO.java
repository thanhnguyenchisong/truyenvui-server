package com.sky.tv.comics.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sky.tv.comics.entity.ProcessEnum;
import com.sky.tv.comics.entity.SourceEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import java.util.Set;
import java.util.UUID;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Schema(
    description = "ComicDTO Model Information"
)
@EqualsAndHashCode(callSuper = true)
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ComicDTO extends BaseDTO<UUID>{

  @Schema(
      description = "Comic name"
  )
  @NotEmpty(message = "Should not be null or empty")
  private String name;

  @Schema(
      description = "Comic cover image path "
  )
  @NotEmpty(message = "Should not be null or empty")
  private String image;

  @Schema(
      description = "Comic description "
  )
  @NotEmpty(message = "Should not be null or empty")
  private String description;

  @Schema(
      description = "number view"
  )
  private int numberView;

  @Schema(
      description = "number like"
  )
  private int numberLike;

  private ProcessEnum status;

  private SourceEnum source;
  
  private Set<String> categoryIDs;
}
