package com.sky.tv.comics.controller;

import com.sky.tv.comics.constant.ResponseDefault;
import com.sky.tv.comics.dto.ComicDTO;
import com.sky.tv.comics.dto.paging.PagingResponse;
import com.sky.tv.comics.dto.request.GetComicPaging;
import com.sky.tv.comics.dto.request.GetTypeDTO;
import com.sky.tv.comics.dto.response.ComicGroupDTO;
import com.sky.tv.comics.entity.ComicAnalysis;
import com.sky.tv.comics.exception.ComicBusinessException;
import com.sky.tv.comics.service.ComicAnalysisService;
import com.sky.tv.comics.service.ComicService;
import com.sky.tv.comics.service.ComicServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.ws.rs.QueryParam;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
  private final ComicAnalysisService comicAnalysisService;

  @Operation(
      summary = "Get Comics REST API",
      description = "Get Comics REST API by IDs from Database"
  )
  @ApiResponse(
      responseCode = "200",
      description = "HTTP Status 200 OK"
  )
  @PostMapping(value = "get", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<ComicDTO>> get(@RequestBody List<UUID> ids) {
    return ResponseEntity.status(HttpStatus.OK).body(comicService.get(ids));
  }

  @Operation(
      summary = "Create Comics REST API",
      description = "Create Comics REST API"
  )
  @ApiResponse(
      responseCode = "201",
      description = "HTTP Status 200 CREATED"
  )
  @PostMapping(value = "create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ResponseDefault> create(@RequestBody @Valid List<ComicDTO> comicDTOs) {
    comicService.create(comicDTOs);
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
  @PostMapping(value = "update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ResponseDefault> update(@RequestBody @Valid List<ComicDTO> comicDTOs)
      throws ComicBusinessException {
    comicService.update(comicDTOs);
    return ResponseEntity.status(HttpStatus.OK).body(ResponseDefault.UPDATED);
  }

  @Operation(
      summary = "Get popular Comics REST API",
      description = "Get popular Comics REST API"
  )
  @ApiResponse(
      responseCode = "200",
      description = "HTTP Status 200 OK"
  )
  @GetMapping(value = "get/popular", consumes = MediaType.APPLICATION_JSON_VALUE, produces =
      MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ComicGroupDTO> get(@QueryParam(value = "quality") int quality) {
    comicService.getPopular(quality);
    return null;
  }

  @Operation(
      summary = "Get Comics REST API",
      description = "Get Comics REST API by a list Group or Category"
  )
  @ApiResponse(
      responseCode = "200",
      description = "HTTP Status 200 OK"
  )
  @PostMapping(value = "get", consumes = MediaType.APPLICATION_JSON_VALUE, produces =
      MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ComicGroupDTO> get(@RequestBody List<String> names,
      @QueryParam(value = "type") GetTypeDTO type) {
    return null;
  }

  @Operation(
      summary = "Get Comics REST API",
      description = "Get Comics REST API by a Group or Category having paging"
  )
  @ApiResponse(
      responseCode = "200",
      description = "HTTP Status 200 OK"
  )
  @PostMapping(value = "get", consumes = MediaType.APPLICATION_JSON_VALUE, produces =
      MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<PagingResponse<ComicDTO>> get(@RequestBody GetComicPaging comicPaging) {
    return null;
  }
}
