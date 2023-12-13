package com.sky.tv.comics.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import java.util.UUID;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Schema(
		description = "ComicDTO Model Information"
)
@EqualsAndHashCode(callSuper = true)
@Data
public class ComicDTO extends BaseDTO {

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
}
