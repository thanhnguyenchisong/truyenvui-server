package com.sky.tv.comics.controller;

import com.sky.tv.comics.constant.ResponseDefault;
import com.sky.tv.comics.dto.ComicDTO;
import com.sky.tv.comics.entity.Comic;
import com.sky.tv.comics.service.ComicService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(
		name = "CRUD Rest APIs for Comic Resource",
		description = "CRUD REST APIs - get, create, update, delete"
)
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("comic")
public class ComicController {

	private final ComicService comicService;

	@Operation(
			summary = "Get Comics REST API",
			description = "Get Comics REST API by IDs from Database"
	)
	@ApiResponse(
			responseCode = "200",
			description = "HTTP Status 200 OK"
	)
	@PostMapping(value = "get", consumes = MediaType.APPLICATION_JSON_VALUE ,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Comic>> get(@RequestBody List<String> ids) {
		return null;
	}

	@Operation(
			summary = "Create Comics REST API",
			description = "Create Comics REST API"
	)
	@ApiResponse(
			responseCode = "201",
			description = "HTTP Status 200 CREATED"
	)
	@PostMapping(value = "create", consumes = MediaType.APPLICATION_JSON_VALUE ,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDefault> create(@RequestBody @Valid List<ComicDTO> comicDTOs) {
		comicService.createComic(comicDTOs);
		return ResponseEntity.status(HttpStatus.CREATED).body(ResponseDefault.CREATED);
	}

	@Operation(
			summary = "Update Comics REST API",
			description = "Update Comics REST API"
	)
	@ApiResponse(
			responseCode = "200",
			description = "HTTP Status 200 OK"
	)
	@PostMapping(value = "update", consumes = MediaType.APPLICATION_JSON_VALUE ,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> update(@RequestBody @Valid List<ComicDTO> comicDTO) {
		return null;
	}

}
