package com.sky.tv.comics.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class ComicDTO {

	@NotEmpty(message = "Should not be null or empty")
	private String name;
	@NotEmpty(message = "Should not be null or empty")
	private String imagePath;
	@NotEmpty(message = "Should not be null or empty")
	private String description;

}
