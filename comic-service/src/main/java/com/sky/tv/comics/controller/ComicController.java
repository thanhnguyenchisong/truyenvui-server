package com.sky.tv.comics.controller;

import com.sky.tv.comics.dto.response.ResponseDefault;
import com.sky.tv.comics.dto.ComicDTO;
import com.sky.tv.comics.dto.request.GetComicPaging;
import com.sky.tv.comics.dto.response.BundlePagingResponse;
import com.sky.tv.comics.exception.BusinessException;
import com.sky.tv.comics.service.ComicService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.ws.rs.QueryParam;
import java.text.ParseException;
import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
@RequestMapping("comics")
public class ComicController {

  private final ComicService comicService;

  @Operation(
      summary = "Get Comics REST API by ID",
      description = "Get Comics REST API by ID"
  )
  @ApiResponse(
      responseCode = "200",
      description = "HTTP Status 200 OK"
  )
  @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ComicDTO> get(@PathVariable UUID id) {
    return ResponseEntity.ok(comicService.get(id));
  }

  @Operation(
      summary = "Get Comics REST API by IDs",
      description = "Get Comics REST API by IDs"
  )
  @ApiResponse(
      responseCode = "200",
      description = "HTTP Status 200 OK"
  )
  @PostMapping(value = "/batch", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<ComicDTO>> get(@RequestBody List<UUID> ids) {
    return ResponseEntity.ok(comicService.get(ids));
  }

  @Operation(
      summary = "Create Comics REST API",
      description = "Create Comics REST API"
  )
  @ApiResponse(
      responseCode = "201",
      description = "HTTP Status 201 CREATED"
  )
  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
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
  @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ResponseDefault> update(@RequestBody @Valid List<ComicDTO> comicDTOs)
      throws BusinessException {
    comicService.update(comicDTOs);
    return ResponseEntity.status(HttpStatus.OK).body(ResponseDefault.UPDATED);
  }

  /**
   * For slide bar
   * @param quality
   * @return
   * @throws ParseException
   */
  @Operation(
      summary = "Get popular Comics REST API",
      description = "Get popular Comics REST API"
  )
  @ApiResponse(
      responseCode = "200",
      description = "HTTP Status 200 OK"
  )
  @GetMapping(value = "popular", produces =
      MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<ComicDTO>> get(@QueryParam(value = "quality") int quality) throws ParseException {
    return ResponseEntity.status(HttpStatus.OK).body(comicService.getComicPopular(quality));
  }

  /**
   * For group in home page and category
   * @param comicPaging
   * @return
   */
  @Operation(
      summary = "Get Comics REST API",
      description = "Get Comics REST API by a Group or Category having paging"
  )
  @ApiResponse(
      responseCode = "200",
      description = "HTTP Status 200 OK"
  )
  @PostMapping(value = "comic-paging", produces =
      MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<BundlePagingResponse<ComicDTO>>> get(@RequestBody GetComicPaging comicPaging) {
    return ResponseEntity.status(HttpStatus.OK).body(comicService.getComicPaging(comicPaging));
  }

private static final Logger logger = LoggerFactory.getLogger(ComicController.class);
  @GetMapping(value = "test-log", produces =
          MediaType.APPLICATION_JSON_VALUE)
  public String log() {
      log.info("This is info with lombl");
      log.error("This is error with lombl");
      logger.info("This is info");
      logger.error("This is error");
      return "Logged";
  }

}
